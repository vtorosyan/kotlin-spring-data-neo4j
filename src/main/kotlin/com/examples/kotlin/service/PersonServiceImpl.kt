package com.examples.kotlin.service

import com.examples.kotlin.annotations.TransactionalService
import com.examples.kotlin.domain.Person
import com.examples.kotlin.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired

@TransactionalService
internal class PersonServiceImpl @Autowired constructor(val personRepository: PersonRepository?): PersonService {

    override fun findByName(name: String): List<Person> {
        return personRepository!!.findByNameIgnoreCaseContaining(name)
    }

}