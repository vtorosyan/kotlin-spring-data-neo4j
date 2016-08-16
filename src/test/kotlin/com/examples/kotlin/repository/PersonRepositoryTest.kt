package com.examples.kotlin.repository

import com.examples.kotlin.Neo4jTestConfig
import com.examples.kotlin.domain.Person
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
class PersonRepositoryTest {

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
    fun `Returns list of persons when trying to find by existing name`() {
        val persons: List<Person> = personRepository.findByNameIgnoreCaseContaining("cap")

        assertNotNull(persons)
        assertEquals(1, persons.size)
        assertEquals("Leonardo DiCaprio", persons[0].name)
    }

    @Test
    @DirtiesContext
    fun `Returns empty list when trying to find with non existing name`() {
        val persons: List<Person> = personRepository.findByNameIgnoreCaseContaining("test")

        assertNotNull(persons)
        assertEquals(0, persons.size)
    }

}