package com.jordijaspers.webfluxapiannotations.repository;

import com.jordijaspers.webfluxapiannotations.model.StarWarsCharacter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * The repository for the Star Wars API.
 *
 * @see <a href="https://swapi.co/">Star Wars API</a>
 */
public interface StarWarsRepository {

    /**
     * This method makes a GET request to the Star Wars API and returns all the characters resembling the search term.
     *
     * @param searchCriteria The search term.
     * @return A Flux of Star Wars characters.
     */
    Flux<StarWarsCharacter> findStarWarsCharacterByCriteria(final String searchCriteria);

}
