package com.example.mvvmexercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainViewModel :
    ViewModel(),
    IMainViewModel {
    private val _time = MutableStateFlow(0)
    private val _isRunning = MutableStateFlow(false)

    override val time: StateFlow<Int> = _time.asStateFlow()

    override val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private var job: Job? = null

    override fun start() {
        viewModelScope.launch {
            if (!isRunning.value) {
                _isRunning.value = true
                job =
                    launch {
                        while (isRunning.value) {
                            _time.value++
                            delay(1.seconds)
                        }
                    }
            }
        }
    }

    override fun stop() {
        job?.cancel()
        _isRunning.value = false
    }
}
