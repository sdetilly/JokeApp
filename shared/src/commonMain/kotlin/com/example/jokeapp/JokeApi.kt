package com.example.jokeapp

import com.example.jokeapp.model.Joke
import com.example.jokeapp.model.JokeSearch
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class JokeApi {
    private val client = httpClient {
        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private val address = Url("https://icanhazdadjoke.com/")

    suspend fun getJoke(): Joke {
        val response = client.get {
            url(address.toString())
        }
        return response.body()
    }

    suspend fun getJokeSearchResult(searchTerm: String): JokeSearch {
        val response = client.get {
            url("${address}search?term=$searchTerm")
        }
        return response.body()
    }
}
