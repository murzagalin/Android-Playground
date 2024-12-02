package com.github.murzagalin.androidplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.murzagalin.androidplayground.ui.theme.AndroidPlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AndroidPlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        MainContent(
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
fun BoxScope.MainContent(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {

    val data = viewModel.usersFlow.collectAsState()
    val value = data.value

    when (value) {
        MainViewModel.ViewState.Empty -> {}
        MainViewModel.ViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    "Loading...",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is MainViewModel.ViewState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    "Error: ${value.error.message}",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is MainViewModel.ViewState.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(value.users) {
                        Text(
                            "${it.username}. ${it.email}",
                            modifier = Modifier.padding(
                                all = 12.dp
                            )
                        )
                    }
                }
            }
        }
    }


    /*
        val interactionSource = remember { MutableInteractionSource() }
        val pressed by interactionSource.collectIsPressedAsState()
        val scale: Float by animateFloatAsState(if (pressed) 0.97f else 1f, label = "scale")
        val elevation: Dp by animateDpAsState(if (pressed) 1.dp else 8.dp, label = "elevation")
        val paddingColor: Color by animateColorAsState(if (pressed) Color.White else Color.Blue, label = "color")


        Box(
            modifier = Modifier
                .scale(scale)
                .graphicsLayer {
                    shadowElevation = elevation.toPx()
                }
                .background(Color.Red)
                .padding(16.dp)
                .size(90.dp)
                .background(paddingColor)
                .padding(8.dp)
                .align(Alignment.Center)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {}
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Cyan)
            )
        }*/
}


