package com.example.jokeapp.viewModel

import com.example.jokeapp.CFlow
import com.example.jokeapp.ViewModel

abstract class SearchJokeViewModel : ViewModel() {
    abstract val jokeList: CFlow<List<String>>

    abstract fun getJokeWithTerm(searchTerm: String)
}
