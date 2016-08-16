package com.examples.kotlin.service

import com.examples.kotlin.domain.Movie
import com.examples.kotlin.repository.MovieRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.mockito.runners.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
class MovieServiceTest {

    @InjectMocks
    private lateinit var movieService: MovieServiceImpl

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun prepare() {
        doReturn(buildMovies()).`when`(movieRepository).findByTitleIgnoreCaseContaining("tit")
    }

    @Test
    fun `Returns empty list when trying to retrieve by title given NULL as parameter`() {
        val movies: List<Movie> = movieService.retrieveByTitle(null)

        assertNotNull(movies)
        assertEquals(0, movies.size)
    }

    @Test
    fun `Returns list of movies when retrieving by existing title`() {
        val movies: List<Movie> = movieService.retrieveByTitle("tit")

        assertNotNull(movies)
        assertEquals(2, movies.size)
    }

    @Test
    fun `Returns empty list when exception is thrown while retrieving by title`() {
        doThrow(StubException()).`when`(movieRepository).findByTitleIgnoreCaseContaining(Mockito.anyString())

        val movies: List<Movie> = movieService.retrieveByTitle("tit")

        assertNotNull(movies)
        assertEquals(0, movies.size)
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

    private class StubException : RuntimeException()
}