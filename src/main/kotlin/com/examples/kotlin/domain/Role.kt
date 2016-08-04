package com.examples.kotlin.domain

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.voodoodyne.jackson.jsog.JSOGGenerator
import org.neo4j.ogm.annotation.*

@RelationshipEntity(type = "ACTED_IN")
@JsonIdentityInfo(generator = JSOGGenerator::class)
class Role {

    @GraphId
    var id: Long? = null
    var roles: MutableList<String>? = null
    @StartNode
    var person: Person? = null
    @EndNode
    var movie: Movie? = null
    @Relationship(type = "ACTED_IN")
    var movies: MutableList<Movie>? = null

}