package edu.maksimchuk.lab1;

/**
 * @author Tom
 * @version 1.0.0
 * @project lab1
 * @class ProductService
 * @since 29.09.2025 - 22.13
 */
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return repository.findById(id);
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(String id, Product productDetails) {
        Product product = repository.findById(id).orElseThrow();
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        return repository.save(product);
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }
}
