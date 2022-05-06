package com.example.jokeapp

import kotlinx.coroutines.CoroutineScope

expect open class ViewModel() {
    protected val viewModelScope: CoroutineScope
    protected open fun onCleared()
}
