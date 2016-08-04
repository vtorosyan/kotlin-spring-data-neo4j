package com.examples.kotlin.domain

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.voodoodyne.jackson.jsog.JSOGGenerator
import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
@JsonIdentityInfo(generator = JSOGGenerator::class)
data class Person(

    @GraphId
    var id: Long? = null,
    var name: String? = null,
    var born: Int? = null,
    @Relationship(type = "ACTED_IN")
    var movies: MutableList<Movie>? = null

)