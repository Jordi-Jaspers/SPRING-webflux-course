package com.jordijaspers.webfluxapiannotations.controller;

import com.jordijaspers.webfluxapiannotations.model.Product;
import com.jordijaspers.webfluxapiannotations.model.ProductEvent;
import com.jordijaspers.webfluxapiannotations.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * The Controller class for the Product entity.
 * Testing this controller can be done with postman or a simple curl command.
 *
 * example curl command: curl -v http://localhost:8080/products
 *
 * Note: because of the simplicity, there is no service layer and the repository is used directly.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    /**
     * The ProductRepository is injected by Spring.
     */
    private final ProductRepository repository;

    /**
     * The default constructor.
     *
     * @param repository the ProductRepository
     */
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all products from the database.
     *
     * @return a Flux of products.
     */
    @GetMapping
    public Flux<Product> getProducts() {
        return repository.findAll();
    }

    /**
     * Get a product by id.
     *
     * @param id the id of the product.
     * @return a Mono of the product.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getProduct(@PathVariable String id) {
        LOGGER.info("Getting product with id: {}", id);
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Create a new product and save it in the database.
     *
     * @param product the product to create.
     * @return A Mono of the created product with a "CREATED" status.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> saveProduct(@RequestBody Product product) {
        LOGGER.info("Saving product: {}", product);
        return repository.save(product);
    }

    /**
     * Update an existing product in the database.
     *
     * @param id      the id of the product to update.
     * @param product the product to update.
     * @return A Mono of the updated product with an "OK" status, or a "NOT FOUND" status if the product does not exist.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        LOGGER.info("Updating product with id: {}", id);
        return repository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    return repository.save(existingProduct);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Delete a product from the database.
     * @param id the id of the product to delete.
     * @return A Mono of the deleted product with an "OK" status, or a "NOT FOUND" status if the product does not exist.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable("id") String id) {
        LOGGER.info("Deleting product with id: {}", id);
        return repository.findById(id)
                .flatMap(existingProduct -> repository.delete(existingProduct).then(Mono.just(ResponseEntity.ok().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Delete all products from the database.
     *
     * @return A Mono of the deleted products with an "OK" status.
     */
    @DeleteMapping
    public Mono<ResponseEntity<Void>> deleteAllProducts() {
        LOGGER.info("Deleting all products");
        return repository.deleteAll()
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    /**
     * Because of "MediaType.TEXT_EVENT_STREAM_VALUE" this method will return an event stream of products.
     * @return a Flux of products events.
     */
    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductEvent> getProductsEvents() {
        return Flux.interval(Duration.ofSeconds(1)).map(i -> new ProductEvent(i,"Product Event"));
    }
}
