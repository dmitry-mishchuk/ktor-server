package com.example.routes

import com.example.models.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.usersRouting(){
    route("api/users"){
        get {
            //call.respondText("Hello users!!!")
            call.response.status(HttpStatusCode.OK)
            call.respond(mapOf("users" to users))
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText("Error with id", status= HttpStatusCode.BadRequest)
            val user = users.find{ it.id == id.toInt() }  ?: return@get call.respondText("Not found user whith id=$id", status=HttpStatusCode.NotFound )
            call.respond(user)
        }
        post {
            //users += call.receive<User>()
            val params = call.receiveParameters()
            var user = User(params["id"]!!.toInt(), params["firstName"].toString(), params["lastName"].toString())
            print(params["firstName"].toString())
            users.add(user)
            call.respond(mapOf("users" to users))
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (users.removeIf {it.id == id.toInt()}){
                call.respondText("OK", status=HttpStatusCode.Accepted )
            } else {
                call.respondText("Not found", status=HttpStatusCode.NotFound )
            }
        }

    }
}

fun Application.UsersRouts(){
    routing {
        usersRouting()
    }
}

var users = mutableListOf<User>(
    User(1, "Bil", "Kolins"),
    User(2, "Mark", "Twen")

)