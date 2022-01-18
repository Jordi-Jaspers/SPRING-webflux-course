package com.jordijaspers.webfluxapiannotations.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.netty.handler.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

/**
 * A web client class that makes requests to the Star Wars API.
 */
@Configuration
public class StarWarsWebClientConfig {

    /**
     * The base URL of the Star Wars API.
     */
    private static final String BASE_URL = "https://swapi.dev/api/";

    /**
     * The webclient configuration for the PPE SSO endpoint.
     */
    @Bean
    public WebClient starWarsWebClient(final ObjectMapper baseConfig) {
        final HttpClient httpClient = HttpClient.create()
                .wiretap("StarWarsWebClient", LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL);

        final ObjectMapper objectMapper = baseConfig.copy();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        final ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                }).build();

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .baseUrl(BASE_URL)
                .build();
    }
}
