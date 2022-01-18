package com.jordijaspers.webfluxapiannotations;

import com.jordijaspers.webfluxapiannotations.model.Product;
import com.jordijaspers.webfluxapiannotations.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableConfigurationProperties
@SuppressWarnings({"HideUtilityClassConstructor", "PMD.UseUtilityClass"})
public class WebfluxApiAnnotationsApplication {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebfluxApiAnnotationsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApiAnnotationsApplication.class, args);
    }

    /**
     * CommandLineRunner is a Spring Boot functional interface that allows to run code after the application has been started.
     * For example, we can use it to insert some data in the database.
     *
     * @param productRepository the product repository
     * @return a CommandLineRunner
     */
    @Bean
    CommandLineRunner init(final ProductRepository productRepository) {
        return args -> {
            LOGGER.info("Inserting products into the database...");

            Flux<Product> productFlux = Flux.just(
                            new Product(null, "Darth Vader Figure 1", 15.99),
                            new Product(null, "Luke Skywalker Figure 1", 20.00),
                            new Product(null, "Darth Vader Figure 2", 17.50))
                    .flatMap(productRepository::save);

            LOGGER.info("The products are inserted into the database successfully...");

            productFlux
                    .thenMany(productRepository.findAll())
                    .subscribe(product -> LOGGER.info("Product: {}", product));
        };
    }

}
