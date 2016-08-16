package com.examples.kotlin.service

import com.examples.kotlin.domain.Person
import com.examples.kotlin.repository.PersonRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.runners.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
class PersonServiceTest {

    @InjectMocks
    private lateinit var personService: PersonServiceImpl

    @Mock
    private lateinit var personRepository: PersonRepository

    @Before
    fun prepare() {
        doReturn(buildPersons()).`when`(personRepository).findByNameIgnoreCaseContaining("leo")
    }

    @Test
    fun `Returns list of persons when trying to find by existing name`() {
        val persons: List<Person> = personService.findByName("leo")

        assertNotNull(persons)
        assertEquals(2, persons.size)
    }

    @Test
    fun `Returns empty list of persons when trying to find by not existing name`() {
        val persons: List<Person> = personService.findByName("test")

        assertNotNull(persons)
        assertEquals(0, persons.size)
    }

    private fun buildPersons(): List<Person> {
        val person1 = Person()
        person1.name = "Leo DiCaprio"

        val person2 = Person()
        person2.name = "Leo Carpaccio"

        return arrayListOf(person1, person2)
    }

}