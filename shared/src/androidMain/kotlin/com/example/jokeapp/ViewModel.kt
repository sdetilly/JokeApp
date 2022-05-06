package com.example.jokeapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual open class ViewModel : ViewModel() {
    protected actual val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    actual override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}
