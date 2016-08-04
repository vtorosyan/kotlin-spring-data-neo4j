package com.examples.kotlin.controller

import com.examples.kotlin.domain.Movie
import com.examples.kotlin.service.MovieService
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.reset
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class MovieControllerTest : AbstractMvcTest() {

    @Autowired
    private lateinit var movieService: MovieService

    @Before
    fun setup() {
        reset(movieService)
        doReturn(buildMovies()).`when`(movieService).retrieveByTitle("tit")
        doReturn(hashMapOf(Pair("title", "Titanic"), Pair("actor", "Leo"))).`when`(movieService).graph(100)
    }

    @Test
    fun retrieveByTitle_GivenTitleExists_ReturnMovies() {

        get("/movies/tit")
                .andExpect(status().isOk)
                .andExpect(jsonPath<MutableCollection<out Int>>("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].title", equalTo("Titanic")))
                .andExpect(jsonPath("$[0].released", `is`(1900)))

    }

    @Test
    fun retrieveByTitle_GivenTitleDoesNotExist_ReturnNotFound() {

        get("/movies/test")
                .andExpect(status().isNotFound)

    }

    @Test
    fun displayD3Graph_DataExists_ReturnResponse() {

        get("/movies/graph")
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.title", equalTo("Titanic")))
                .andExpect(jsonPath("$.actor", equalTo("Leo")))

    }

    private fun buildMovies(): List<Movie> {
        val movie1 = Movie()
        movie1.title = "Titanic"
        movie1.released = 1900

        val movie2 = Movie()
        movie2.title = "J. Edgar (Tit.)"
        movie2.released = 2011

        return arrayListOf(movie1, movie2)
    }

}