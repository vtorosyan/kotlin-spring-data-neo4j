package com.examples.kotlin.repository

import com.examples.kotlin.domain.Person
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.GraphRepository
import org.springframework.data.repository.query.Param

interface PersonRepository : GraphRepository<Person> {

    @Query("MATCH (p:Person) WHERE p.name =~ ('(?i).*'+{name}+'.*') RETURN p")
    fun findByNameIgnoreCaseContaining(@Param("name") name: String): List<Person>

}