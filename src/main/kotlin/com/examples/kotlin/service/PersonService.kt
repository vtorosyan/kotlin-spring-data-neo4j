package com.examples.kotlin.service

import com.examples.kotlin.domain.Person

interface PersonService {

    fun findByName(name: String): List<Person>

}