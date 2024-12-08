package com.example.mvvmexercise

import kotlinx.coroutines.flow.StateFlow

interface IMainViewModel {
    val time: StateFlow<Int>
    val isRunning: StateFlow<Boolean>

    fun start()

    fun stop()
}
