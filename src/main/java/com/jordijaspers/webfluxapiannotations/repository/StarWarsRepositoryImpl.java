package com.jordijaspers.webfluxapiannotations.repository;

import com.jordijaspers.webfluxapiannotations.model.StarWarsCharacter;
import com.jordijaspers.webfluxapiannotations.model.StarWarsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * {@inheritDoc}.
 */
@Repository
public class StarWarsRepositoryImpl implements StarWarsRepository {

    /**
     * The webclient with the 'https://swapi.dev/api/people/' endpoint configured.
     */
    private final WebClient webClient;

    /**
     * The constructor that creates the webclient to be used in the API requests.
     */
    @Autowired
    public StarWarsRepositoryImpl(@Qualifier("starWarsWebClient") final WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * {@inheritDoc}.
     */
    @SuppressWarnings("PMD.LawOfDemeter")
    public Flux<StarWarsCharacter> findStarWarsCharacterByCriteria(final String searchCriteria) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("people/")
                        .queryParam("search", searchCriteria)
                        .build())
                .retrieve()
                .bodyToFlux(StarWarsResult.class)
                .flatMapIterable(StarWarsResult::getResults);
    }
}
