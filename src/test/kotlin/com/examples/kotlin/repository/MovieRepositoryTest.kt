package com.examples.kotlin.repository

import com.examples.kotlin.Neo4jTestConfig
import com.examples.kotlin.domain.Movie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Neo4jTestConfig::class))
@ActiveProfiles(profiles = arrayOf("test"))
class MovieRepositoryTest {

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Before
    fun prepare() {
        DbData(this.movieRepository, this.personRepository)
    }

    @Test
    @DirtiesContext
    fun findByTitleIgnoreCaseContaining_TitleContainsLetters_ReturnMovieList() {
        val movies: List<Movie>  = movieRepository.findByTitleIgnoreCaseContaining("tit")

        assertNotNull(movies)
        assertEquals(1, movies.size)
    }

    @Test
    @DirtiesContext
    fun findByTitleIgnoreCaseContaining_TitleDoesNotContain_ReturnEmptyList() {
        val movies: List<Movie>  = movieRepository.findByTitleIgnoreCaseContaining("test")

        assertNotNull(movies)
        assertEquals(0, movies.size)
    }

    @Test
    @DirtiesContext
    fun graph() {
        val graph: List<Map<String, Any>>  = movieRepository.graph(100)

        assertEquals(1, graph.size)
        assertEquals(2, graph[0].size)

        val casts: Array<String> = graph[0]["cast"] as Array<String>
        assertEquals("Titanic", graph[0]["movie"])
        assertEquals("Leonardo DiCaprio", casts[0])
    }

}