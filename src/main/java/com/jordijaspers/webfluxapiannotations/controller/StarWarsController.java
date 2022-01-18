package com.jordijaspers.webfluxapiannotations.controller;

import com.jordijaspers.webfluxapiannotations.model.StarWarsCharacter;
import com.jordijaspers.webfluxapiannotations.service.StarWarsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Controller class for the star wars API consummation.
 */
@RestController
@RequestMapping("/starwars")
public class StarWarsController {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StarWarsController.class);

    /**
     * The star wars Service.
     */
    private final StarWarsService service;

    /**
     * The default constructor.
     */
    @Autowired
    public StarWarsController(final StarWarsService service) {
        this.service = service;
    }

    /**
     * Get all the star wars characters by search criteria.
     *
     * @return a Flux of StarWars Characters.
     */
    @GetMapping("/{searchCriteria}")
    public Flux<StarWarsCharacter> getStarWarsCharactersByCriteria(@PathVariable final String searchCriteria) {
        LOGGER.info("Getting all the star wars characters by search criteria: {}", searchCriteria);
        return service.getStarWarsCharactersByCriteria(searchCriteria).switchIfEmpty(
                        Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "The force is not with you")));
    }

}
