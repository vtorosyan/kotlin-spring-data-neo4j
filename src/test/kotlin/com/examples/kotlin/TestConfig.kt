package com.examples.kotlin

import com.examples.kotlin.service.MovieService
import com.examples.kotlin.service.PersonService
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
open class TestConfig {

    @Bean
    @Profile("test")
    open fun personService(): PersonService {
        return Mockito.mock(PersonService::class.java)
    }

    @Bean
    @Profile("test")
    open fun movieService(): MovieService {
        return Mockito.mock(MovieService::class.java)
    }

}