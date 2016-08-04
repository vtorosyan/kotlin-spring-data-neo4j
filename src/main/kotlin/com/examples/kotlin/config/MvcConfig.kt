package com.examples.kotlin.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@Import(Neo4jConfig::class)
open class MvcConfig : WebMvcConfigurerAdapter()
