package com.github.murzagalin.androidplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.murzagalin.androidplayground.ui.theme.AndroidPlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AndroidPlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Greeting(
                            name = "Android",
                            viewModel = viewModel,
                            modifier = Modifier
                                .padding(innerPadding)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {

    val data by viewModel.dataFlow.collectAsState()

    Text(
        text = "Hello $name!\n$data",
        modifier = modifier
    )
}
