package com.examples.kotlin.repository

import com.examples.kotlin.domain.Movie
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.GraphRepository
import org.springframework.data.repository.query.Param

interface MovieRepository : GraphRepository<Movie> {

    @Query("MATCH (m:Movie) WHERE m.title =~ ('(?i).*'+{title}+'.*') RETURN m")
    fun findByTitleIgnoreCaseContaining(@Param("title") title: String): List<Movie>

    @Query("MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) RETURN m.title as movie, collect(a.name) as cast LIMIT {limit}")
    fun graph(@Param("limit") limit: Int): List<Map<String, Any>>

}