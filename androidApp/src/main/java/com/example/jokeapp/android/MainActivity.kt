package com.example.jokeapp.android

import android.content.Intent
import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokeapp.viewModel.MainNavigationDelegate
import com.example.jokeapp.android.base.BaseActivity
import com.example.jokeapp.viewModel.MainViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainActivity : BaseActivity<MainViewModel>(), MainNavigationDelegate {

    override val viewModelFromServiceLocator = serviceLocator.mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigationDelegate = this
        setContent {
            Joke(textFlow = viewModel.jokeLabel)
        }
    }

    override fun goToSearchPage() {
        startActivity(Intent(this, SearchJokeActivity::class.java))
    }

    @Composable
    fun Joke(textFlow: Flow<String>) {
        val text: String by textFlow.collectAsState("")
        Scaffold(
            topBar = { TopAppBar { Text(text = "Joke App", color = Color.White) } },
            floatingActionButton = { Fab() },
            content = { paddingValues ->  Content(paddingValues, text) }
        )
    }

    @Composable
    fun Fab() = FloatingActionButton(onClick = { viewModel.searchClicked() },
        content = { Icon(Icons.Filled.Search, "") })

    @Composable
    fun Content(paddingValues: PaddingValues, text:String) = Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text= text,
            textAlign = TextAlign.Center,
            modifier = Modifier.wrapContentWidth()
        )
        Button(onClick = { viewModel.getJoke() }) {
            Text(text = "Refresh")
        }
    }

    @Preview
    @Composable
    fun PreviewJoke() {
        val textFlow = flowOf("Hello, This is a joke!")
        Joke(textFlow)
    }
}
