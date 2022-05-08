package com.example.jokeapp.viewModel

import com.example.jokeapp.CFlow
import com.example.jokeapp.JokeApi
import com.example.jokeapp.wrap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchJokeViewModelImpl(private val jokeApi: JokeApi) : SearchJokeViewModel() {
    private val _jokeList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    override val jokeList: CFlow<List<String>> = _jokeList.wrap()

    override fun getJokeWithTerm(searchTerm: String) {
        viewModelScope.launch {
            val jokeSearchResult = jokeApi.getJokeSearchResult(searchTerm)
            _jokeList.value = jokeSearchResult.jokeList.map { it.joke }
        }
    }
}
