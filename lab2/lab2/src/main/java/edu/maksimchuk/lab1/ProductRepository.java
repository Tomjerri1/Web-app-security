package edu.maksimchuk.lab1;

/**
 * @author Tom
 * @version 1.0.0
 * @project lab1
 * @class ProductRepository
 * @since 29.09.2025 - 22.13
 */
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
