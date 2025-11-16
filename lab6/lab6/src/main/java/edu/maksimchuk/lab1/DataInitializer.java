package edu.maksimchuk.lab1;

/**
 * @author Tom
 * @version 1.0.0
 * @project lab1
 * @class DataInitializer
 * @since 29.09.2025 - 22.13
 */
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository repository;

    public DataInitializer(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            repository.save(new Product("Laptop", "Gaming laptop", 1500.0));
            repository.save(new Product("Phone", "Smartphone with good camera", 800.0));
            repository.save(new Product("Headphones", "Wireless headphones", 200.0));
        }
    }
}
