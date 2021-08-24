package com.example.plugins

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.mustache.Mustache
import io.ktor.mustache.MustacheContent
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun Application.configureTemplating() {
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates/mustache")
    }

    routing {
        get("/html-mustache") {
            call.respond(MustacheContent("index.hbs",
                mapOf(
                    "user" to MustacheUser(1, "user1"),
                    "lst" to lst,
                    "mapArr" to mapArr

                )))
        }
    }
}
data class MustacheUser(val id: Int, val name: String)
val lst = listOf<String>("Java", "Kotlin", "JS")
val mapArr = mapOf(
                    "title" to listOf<String>("1", "2", "3")
                  )
