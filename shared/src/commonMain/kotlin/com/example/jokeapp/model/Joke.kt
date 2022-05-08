package com.example.jokeapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Joke(
    val id: String,
    val joke: String
)
