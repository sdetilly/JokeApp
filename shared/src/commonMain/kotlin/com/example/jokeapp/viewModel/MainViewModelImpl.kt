package com.example.jokeapp.viewModel

import com.example.jokeapp.CFlow
import com.example.jokeapp.JokeApi
import com.example.jokeapp.wrap
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

    override fun searchClicked() {
        navigationDelegate?.goToSearchPage()
    }
}
