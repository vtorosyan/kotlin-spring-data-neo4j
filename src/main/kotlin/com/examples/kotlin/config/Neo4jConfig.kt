package com.examples.kotlin.config

import org.neo4j.ogm.session.SessionFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.neo4j.config.Neo4jConfiguration
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableNeo4jRepositories(basePackages = arrayOf("com.examples.kotlin.repository"))
@ComponentScan(basePackages = arrayOf("com.examples.kotlin.service"))
open class Neo4jConfig : Neo4jConfiguration() {

    val dbUrl: String = System.getenv("NEO4J_URL") ?: "http://neo4j:kotlin@localhost:7474"

    @Bean
    open fun getConfiguration(): org.neo4j.ogm.config.Configuration {
        val config = org.neo4j.ogm.config.Configuration()
        config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver").uri = dbUrl
        return config
    }

    override fun getSessionFactory(): SessionFactory {
        return SessionFactory(getConfiguration(), "com.examples.kotlin.domain")
    }

}