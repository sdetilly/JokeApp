package com.example.jokeapp.android

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jokeapp.android.base.BaseActivity
import com.example.jokeapp.viewModel.SearchJokeViewModel
import kotlinx.coroutines.flow.Flow

class SearchJokeActivity : BaseActivity<SearchJokeViewModel>() {
    override val viewModelFromServiceLocator = serviceLocator.searchJokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content(jokeListFlow = viewModel.jokeList)
        }
    }

    @Composable
    fun Content(jokeListFlow: Flow<List<String>>) {
        val jokeList: List<String> by jokeListFlow.collectAsState(initial = emptyList())
        var text by rememberSaveable { mutableStateOf("") }
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(text = "Search Joke", color = Color.White)
                }
            }
        ) { paddingValues ->
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Search Joke") },
                )
                Button(onClick = {
                    Log.d("SearchActivity", "Searching with term: $text")
                    viewModel.getJokeWithTerm(text)
                }) {
                    Text(text = "Search")
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = paddingValues
                ) {
                    items(jokeList) { joke ->
                        Text(text = joke)
                    }
                }
            }
        }
    }
}
