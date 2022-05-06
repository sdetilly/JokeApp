package com.example.jokeapp

import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class JokeApi {
    private val client = httpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    private val address = Url("https://icanhazdadjoke.com/")

    suspend fun getJoke(): Joke  {
        val response = client.get {
            url(address.toString())
        }
        return response.body()
    }
}
