package com.taurunium.viewmodelactivitytest

import android.util.Log
import androidx.lifecycle.ViewModel

class TestViewModel: ViewModel() {

    var counter = 0

    override fun onCleared() {
        super.onCleared()
        Log.d("VidojeMuric", "onCleared: ViewModel destroyed")
    }

    fun increaseCounter(){
        counter++
    }
}