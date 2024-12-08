package com.example.mvvmexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvmexercise.ui.theme.MVVMExerciseTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMExerciseTheme {
                Column(modifier = Modifier.safeContentPadding()) {
                    val time = viewModel.time.collectAsState()
                    val isRunning = viewModel.isRunning.collectAsState()

                    Timer(
                        time = time.value,
                        onStart = viewModel::start,
                        onStop = viewModel::stop,
                        isRunning = isRunning.value,
                    )
                }
            }
        }
    }
}

private fun Int.toFormattedTime(): String =
    when (this) {
        in 0..59 -> "00:${this.fillToTwoDigits()}"
        else -> "${(this / 60).fillToTwoDigits()}:${(this % 60).fillToTwoDigits()}"
    }

private fun Int.fillToTwoDigits(): String =
    when (this) {
        in 0..9 -> "0$this"
        else -> this.toString()
    }

@Composable
private fun Timer(
    time: Int,
    isRunning: Boolean,
    onStart: () -> Unit,
    onStop: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            Modifier
                .fillMaxSize()
                .wrapContentSize()
                .padding(all = 16.dp),
    ) {
        Text("Time: ${time.toFormattedTime()}")

        Row(horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
            Button(
                onClick = onStart,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                enabled = !isRunning,
                modifier = Modifier.weight(weight = 1f),
            ) {
                Text("Start")
            }
            Button(
                onClick = onStop,
                enabled = isRunning,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.weight(weight = 1f),
            ) {
                Text("Stop")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimerPreview() {
    MVVMExerciseTheme {
        Timer(
            time = 61,
            onStart = {},
            onStop = {},
            isRunning = false,
        )
    }
}
