package edu.maksimchuk.lab1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("/public/hello")
    @PreAuthorize("permitAll()")
    public String publicHello() {
        return "Public hello — доступ без логіну";
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    public String userProfile(Authentication auth) {
        return "Доступ дозволено користувачу: " + auth.getName();
    }

    @GetMapping("/manage/stats")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String managerStats(Authentication auth) {
        return "Доступ дозволено: " + auth.getName();
    }

    @GetMapping("/admin/panel")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPanel(Authentication auth) {
        return "Панель адміністратора — доступ дозволено: " + auth.getName();
    }

    @GetMapping("/data/read")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    public String readData(Authentication auth) {
        return "Read data — доступ дозволено: " + auth.getName();
    }

    @PostMapping("/data/write")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String writeData(Authentication auth) {
        return "Write data — дозволено: " + auth.getName();
    }

    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminCreate(Authentication auth) {
        return "Створено адміністратором: " + auth.getName();
    }

    @GetMapping("/manage/forbidden")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String forbiddenForUser(Authentication auth) {
        return "Доступ заборонено USER";
    }

    @GetMapping("/admin/forbidden")
    @PreAuthorize("hasRole('ADMIN')")
    public String forbiddenForManager(Authentication auth) {
        return "Доступ заборонено MANAGER";
    }

    @GetMapping("/data/write/forbidden")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public String forbiddenWrite(Authentication auth) {
        return "Заборонено USER";
    }
}
