package com.taurunium.lekcija

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DetailsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val textViewReceivedText: TextView = findViewById(R.id.textViewReceivedText)
        val textViewTimestamp: TextView = findViewById(R.id.textViewTimestamp)
        val buttonReturnResult: Button = findViewById(R.id.buttonReturnResult)

        // Step 3: Receive and display the passed values
        val enteredText = intent.getStringExtra(Helper.ENTERED_TEXT) ?: "No text provided"
        val timestamp = intent.getLongExtra(Helper.CURRENT_TIMESTAMP, 0L)

        // Convert raw milliseconds into a readable date/time string
        val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .format(Date(timestamp))

        textViewReceivedText.text = "Received: $enteredText"
        textViewTimestamp.text = "Timestamp: $formattedTime"

        buttonReturnResult.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra(Helper.EXTRA_RESULT_MESSAGE, "Welcome back!")
            }
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

    }


}