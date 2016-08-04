package com.examples.kotlin.controller

import com.examples.kotlin.Application
import org.junit.Ignore
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@Ignore
@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Application::class))
@WebAppConfiguration
@ActiveProfiles("test")
open class AbstractMvcTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Throws(Exception::class)
    operator fun get(endpoint: String): ResultActions {
        return mockMvc().perform(MockMvcRequestBuilders.get(endpoint).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
    }

    private fun mockMvc(): MockMvc {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

}