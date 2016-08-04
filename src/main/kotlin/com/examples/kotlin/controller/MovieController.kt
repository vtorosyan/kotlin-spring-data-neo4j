package com.examples.kotlin.controller

import com.examples.kotlin.domain.Movie
import com.examples.kotlin.service.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movies")
class MovieController @Autowired constructor(val movieService: MovieService) {

    @RequestMapping("/graph")
    fun displayD3Graph(@RequestParam(value = "limit", required = false) limit: Int?): Map<String, Any> {
        return movieService.graph(limit ?: 100)
    }

    @RequestMapping("/{title}")
    fun retrieveByTitle(@PathVariable(value = "title") title: String): List<Movie> {
        val movies: List<Movie> = movieService.retrieveByTitle(title)
        if (movies.isEmpty()) throw NotFoundException()
        return movies
    }

}