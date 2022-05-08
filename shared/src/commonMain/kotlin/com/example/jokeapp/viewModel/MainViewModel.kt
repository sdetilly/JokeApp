package com.example.jokeapp.viewModel

import com.example.jokeapp.CFlow
import com.example.jokeapp.ViewModel

abstract class MainViewModel : ViewModel() {
    var navigationDelegate: MainNavigationDelegate? = null

    abstract val jokeLabel: CFlow<String>

    abstract fun getJoke()

    abstract fun searchClicked()
}
