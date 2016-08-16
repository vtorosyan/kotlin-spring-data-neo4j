package com.examples.kotlin.controller

import com.examples.kotlin.domain.Person
import com.examples.kotlin.service.PersonService
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.reset
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class PersonControllerTest : AbstractMvcTest() {

    @Autowired
    private lateinit var personService: PersonService

    @Before
    fun setup() {
        reset(personService)
        doReturn(buildPersons()).`when`(personService).findByName("leo")
    }

    @Test
    fun `Returns HTTP Status 200 and response payload contains list of persons when searching person which exists`() {

        get("/persons/leo")
                .andExpect(status().isOk)
                .andExpect(jsonPath<MutableCollection<out Int>>("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", CoreMatchers.equalTo("Leo DiCaprio")))

    }

    @Test
    fun `Returns HTTP Status 404 when searching person which does not exist`() {

        get("/persons/testme")
                .andExpect(status().isNotFound)

    }

    private fun buildPersons(): List<Person> {
        val person1 = Person()
        person1.name = "Leo DiCaprio"

        val person2 = Person()
        person2.name = "Leo Carpaccio"

        return arrayListOf(person1, person2)
    }

}