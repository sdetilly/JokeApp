package com.example.jokeapp

import com.example.jokeapp.viewModel.MainViewModel
import com.example.jokeapp.viewModel.SearchJokeViewModel

interface ServiceLocator {
    val mainViewModel: MainViewModel
    val searchJokeViewModel: SearchJokeViewModel
}
