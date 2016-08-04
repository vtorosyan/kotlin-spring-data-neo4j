package com.examples.kotlin.service

import com.examples.kotlin.annotations.TransactionalService
import com.examples.kotlin.domain.Movie
import com.examples.kotlin.repository.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@TransactionalService
internal class MovieServiceImpl @Autowired constructor(val movieRepository: MovieRepository?) : MovieService {

    override fun retrieveByTitle(title: String?): List<Movie> {
        val movies: List<Movie> = try {
            movieRepository!!.findByTitleIgnoreCaseContaining(title ?: "")
        } catch (ex: Exception) {
            ArrayList()
        }
        return movies
    }

    override fun graph(limit: Int): Map<String, Any> {
        return transform(movieRepository!!.graph(limit).iterator())
    }

    private fun transform(result: Iterator<Map<String, Any>>): Map<String, Any> {
        val nodes: MutableList<Map<String, Any>> = ArrayList()
        val relations: MutableList<Map<String, Any>> = ArrayList()

        var i = 0
        while (result.hasNext()) {
            val row: Map<String, Any> = result.next()
            nodes.add(map("title", row["movie"]!!, "label", "movie"))

            val target: Int = i++
            for (name: Any? in row["cast"] as Array<*>) {
                val actor = map("title", name!!, "label", "actor")
                var source = nodes.indexOf(actor)
                if (source == -1) {
                    nodes.add(actor)
                    source = i++
                }
                relations.add(map("source", source, "target", target))
            }
        }
        return map("nodes", nodes, "links", relations)
    }

    private fun map(key1: String, value1: Any, key2: String, value2: Any): Map<String, Any> {
        val result = HashMap<String, Any>(2)
        result.put(key1, value1)
        result.put(key2, value2)
        return result
    }

}