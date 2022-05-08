package com.example.jokeapp

import com.example.jokeapp.viewModel.MainViewModelImpl
import com.example.jokeapp.viewModel.SearchJokeViewModelImpl

class ServiceLocatorImpl : ServiceLocator {
    private val jokeApi = JokeApi()

    override val mainViewModel = MainViewModelImpl(jokeApi)

    override val searchJokeViewModel = SearchJokeViewModelImpl(jokeApi)
}
