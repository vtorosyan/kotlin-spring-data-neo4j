package com.examples.kotlin.controller

import com.examples.kotlin.domain.Person
import com.examples.kotlin.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/persons")
class PersonController @Autowired constructor(val personService: PersonService) {

    @RequestMapping("/{name}")
    fun retrieveByName(@PathVariable(value = "name") name: String): List<Person> {
        val persons: List<Person> = personService.findByName(name)
        if (persons.isEmpty()) throw NotFoundException()
        return persons
    }

}