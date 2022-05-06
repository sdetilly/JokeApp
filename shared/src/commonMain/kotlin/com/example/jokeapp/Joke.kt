package com.example.jokeapp

import kotlinx.serialization.Serializable

@Serializable
data class Joke(
    val id: String,
    val joke: String,
    val status: Int
)
