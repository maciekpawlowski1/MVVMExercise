package com.example.mvvmexercise

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class MainViewModel :
    ViewModel(),
    IMainViewModel {
    override val time: StateFlow<Int>
        get() = TODO("Not yet implemented")

    override val isRunning: StateFlow<Boolean>
        get() = TODO("Not yet implemented")

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }
}
