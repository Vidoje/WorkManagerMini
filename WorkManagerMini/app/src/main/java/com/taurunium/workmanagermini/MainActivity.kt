package com.taurunium.workmanagermini

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(FirstWorker::class.java).
            setConstraints(constraints).build()

        val secondWorkRequest = OneTimeWorkRequest.Builder(SecondWorker::class.java)
            .setConstraints(constraints).build()



        val btnClick = findViewById<Button>(R.id.btnClick)
        btnClick.setOnClickListener {
            WorkManager.getInstance(this).beginWith(oneTimeWorkRequest).then(secondWorkRequest).enqueue()
        }
    }
}