/*
 * Copyright (C) ilionx Group BV 2021
 */

package com.jordijaspers.webfluxapiannotations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest(classes = [WebfluxApiAnnotationsApplication.class])
class ApplicationContextTest extends Specification {

    @Autowired
    ApplicationContext applicationContext

    def "Application context can be started"() {
        expect: "application context exists"
        applicationContext
    }
}
