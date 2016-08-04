package com.examples.kotlin

import org.neo4j.ogm.session.SessionFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.neo4j.config.Neo4jConfiguration
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan(basePackages = arrayOf("com.examples.kotlin.service"))
@EnableNeo4jRepositories(basePackages = arrayOf("com.examples.kotlin.repository"))
@Profile("embedded", "test")
open class Neo4jTestConfig : Neo4jConfiguration() {

    @Bean
    open fun getConfiguration(): org.neo4j.ogm.config.Configuration {
        val config = org.neo4j.ogm.config.Configuration()
        config.driverConfiguration().driverClassName = "org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver"
        return config
    }

    override fun getSessionFactory(): SessionFactory {
        return SessionFactory(getConfiguration(), "com.examples.kotlin.domain")
    }

}