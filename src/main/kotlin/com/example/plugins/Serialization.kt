package com.example.plugins

import io.ktor.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
    }

    routing {
        get("/json/jackson") {
            call.response.status(HttpStatusCode.OK)
            call.respond(mapOf("hello" to "world", "java" to mapOf("love" to "world", "javas" to "hi")))
            }
    }
}
