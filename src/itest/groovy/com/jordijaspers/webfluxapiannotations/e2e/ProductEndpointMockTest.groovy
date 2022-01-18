/*
 * Copyright (C) ilionx Group BV 2021
 */

package com.jordijaspers.webfluxapiannotations.e2e


import com.jordijaspers.webfluxapiannotations.controller.ProductController
import com.jordijaspers.webfluxapiannotations.model.Product
import com.jordijaspers.webfluxapiannotations.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification

@SpringBootTest
class ProductEndpointMockTest extends Specification {

    /**
     * The endpoint of the tested controller.
     */
    private final static String BASE_URL = "/products"

    @Autowired
    private ProductController controller

    @Autowired
    private ProductRepository repository

    private WebTestClient client

    private List<Product> expectedProducts = new ArrayList<>()

    def setup() {
        this.client = WebTestClient.bindToController(controller)
                .configureClient()
                .baseUrl(BASE_URL)
                .build()

        this.expectedProducts = repository.findAll().collectList().block();
    }

    def "The result should be the same as the expected products"() {
        when: "Retrieving all products"
        def response = client.get().exchange()

        then: "The result should be the same as the expected products"
        response.expectStatus().isOk()
                .expectBodyList(Product.class).isEqualTo(expectedProducts)
    }
}
