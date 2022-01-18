package com.jordijaspers.webfluxapiannotations.repository;

import com.jordijaspers.webfluxapiannotations.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * The interface Product repository.
 */
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    // No need for extra methods here, Spring Data ReactiveMongoRepository will take care of it.
    // ReactiveMongoRepository also extends from CrudRepository, so we can use the standard CRUD methods or create custom ones.
    // example: Flux<Product> findByName(String name);

}
