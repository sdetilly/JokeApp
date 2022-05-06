package com.example.jokeapp.android

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokeapp.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = App.instance.serviceLocator.mainViewModel

        setContent {
            Joke(textFlow = viewModel.jokeLabel)
        }
    }

    @Composable
    fun Joke(textFlow: Flow<String>) {
        val text: String by textFlow.collectAsState("initial")
        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
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
    }

    @Preview
    @Composable
    fun PreviewJoke() {
        val textFlow = flowOf("Hello, This is a joke!")
        Joke(textFlow)
    }
}
