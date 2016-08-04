package com.examples.kotlin.domain

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.voodoodyne.jackson.jsog.JSOGGenerator
import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
@JsonIdentityInfo(generator = JSOGGenerator::class)
data class Movie(

    @GraphId
    var id: Long? = null,
    var title: String? = null,
    var released: Int? = null,
    var tagline: String? = null,
    @Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
    var roles: MutableList<Role>? = null

)