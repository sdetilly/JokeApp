package com.example.jokeapp

abstract class MainViewModel : ViewModel() {
    abstract val jokeLabel: CFlow<String>

    abstract fun getJoke()
}
