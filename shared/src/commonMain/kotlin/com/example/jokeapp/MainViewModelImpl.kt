package com.example.jokeapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModelImpl(private val jokeApi: JokeApi) : MainViewModel() {
    private val _jokeLabel = MutableStateFlow("")
    override val jokeLabel: CFlow<String> =  _jokeLabel.wrap()

    init {
        getJoke()
    }

    override fun getJoke() {
        viewModelScope.launch {
            _jokeLabel.value = jokeApi.getJoke().joke
        }
    }
}
