package com.taurunium.workmanagermini

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class FirstWorker(var context: Context, var workerParameters: WorkerParameters): Worker(context, workerParameters) {
    override fun doWork(): Result {
        Thread.sleep(3000)
        makeNotification("First Worker", applicationContext)
        return Result.success() 
    }

    private fun makeNotification(s: String, context: Context) {
        
    }
}