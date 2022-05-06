package com.example.jokeapp

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}