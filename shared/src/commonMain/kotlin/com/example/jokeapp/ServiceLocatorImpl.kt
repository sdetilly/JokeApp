package com.example.jokeapp

class ServiceLocatorImpl : ServiceLocator {
    private val jokeApi = JokeApi()

    override val mainViewModel = MainViewModelImpl(jokeApi)
}
