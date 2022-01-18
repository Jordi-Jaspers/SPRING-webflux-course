package com.jordijaspers.webfluxapiannotations.service;

import com.jordijaspers.webfluxapiannotations.model.StarWarsCharacter;
import com.jordijaspers.webfluxapiannotations.repository.StarWarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * This class is a service for Star Wars characters so that the right responses are returned to the controller.
 */
@Service
public class StarWarsService {

    /**
     * The StarWarsRepository injected by Spring.
     */
    private final StarWarsRepository repository;

    /**
     * The default constructor.
     *
     * @param repository the ProductRepository
     */
    @Autowired
    public StarWarsService(final StarWarsRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all the Star Wars characters by searchCriteria.
     *
     * @return the Star Wars characters
     */
    public Flux<StarWarsCharacter> getStarWarsCharactersByCriteria(final String searchCriteria) {
        return repository.findStarWarsCharacterByCriteria(searchCriteria);
    }
}
