package edu.maksimchuk.lab1;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var alice = User.withUsername("alice")
                .password("alicepass")
                .roles("ADMIN")
                .build();

        var bob = User.withUsername("bob")
                .password("bobpass")
                .roles("MANAGER")
                .build();

        var carol = User.withUsername("carol")
                .password("carolpass")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(alice, bob, carol);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // для навчальних цілей
    }

    @Bean
    public SecurityFilterChain securityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/manage/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "MANAGER", "ADMIN")
                        .requestMatchers("/data/write").hasAnyRole("MANAGER", "ADMIN") // для 403 тесту
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {}) // HTTP Basic
                .csrf(csrf -> csrf.disable()); // відключаємо CSRF для curl / тестів

        return http.build();
    }
}
