package com.examples.kotlin.repository

import com.examples.kotlin.domain.Movie
import com.examples.kotlin.domain.Person
import com.examples.kotlin.domain.Role

class DbData constructor(movieRepository: MovieRepository, personRepository: PersonRepository) {

    init {
        val movie = Movie()
        movie.title = "Titanic"
        movie.released = 1900

        movieRepository.save(movie)

        val person = Person()
        person.name = "Leonardo DiCaprio"
        personRepository.save(person)

        val role = Role()
        role.movie = movie
        role.person = person

        val roleNames: MutableList<String> = arrayListOf("Jack")
        role.roles = roleNames

        movie.roles = arrayListOf(role)
        movieRepository.save(movie)
    }

}