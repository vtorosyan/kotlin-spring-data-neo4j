package com.examples.kotlin.service

import com.examples.kotlin.domain.Movie

interface MovieService {

    fun retrieveByTitle(title: String?): List<Movie>

    fun graph(limit: Int): Map<String, Any>

}