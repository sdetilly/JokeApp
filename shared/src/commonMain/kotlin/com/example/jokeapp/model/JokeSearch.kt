package com.example.jokeapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JokeSearch(
    val search_term: String,
    val total_jokes: Int,
    @SerialName("results") val jokeList: List<Joke>
)
