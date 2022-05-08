package com.example.jokeapp

import com.example.jokeapp.viewModel.MainViewModelImpl
import com.example.jokeapp.viewModel.SearchJokeViewModelImpl

class ServiceLocatorImpl : ServiceLocator {
    private val jokeApi = JokeApi()

    override fun createMainViewModel() = MainViewModelImpl(jokeApi)

    override fun createSearchJokeViewModel() = SearchJokeViewModelImpl(jokeApi)
}
