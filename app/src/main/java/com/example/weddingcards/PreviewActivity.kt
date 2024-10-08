package com.example.weddingcards

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PreviewActivity : AppCompatActivity() {

    private var currentLayoutId: Int = 0 // Variable to store the current layout ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the clicked position and entered text from the intent
        val clickedPosition = intent.getIntExtra("clicked_position", 0)
        val groomName = intent.getStringExtra("groom_name")
        val brideName = intent.getStringExtra("bride_name")
        val eventName = intent.getStringExtra("event_name")
        val eventTime = intent.getStringExtra("event_time")
        val eventDate = intent.getStringExtra("event_date")
        val eventVenue = intent.getStringExtra("event_venue")

        // Choose the layout based on the clicked item and store the current layout ID
        when (clickedPosition) {
            0 -> {
                setContentView(R.layout.preview_layout_1)
                currentLayoutId = R.layout.preview_layout_1
            }
            1 -> {
                setContentView(R.layout.preview_layout_2)
                currentLayoutId = R.layout.preview_layout_2
            }
            2 -> {
                setContentView(R.layout.preview_layout_3)
                currentLayoutId = R.layout.preview_layout_3
            }
            3 -> {
                setContentView(R.layout.preview_layout_4)
                currentLayoutId = R.layout.preview_layout_4
            }
            4 -> {
                setContentView(R.layout.preview_layout_5)
                currentLayoutId = R.layout.preview_layout_5
            }
        }

        // Display the entered text in the respective TextViews
        val groomNameTextView = findViewById<TextView>(R.id.textView)
        val brideNameTextView = findViewById<TextView>(R.id.textView4)
        val eventNameTextView = findViewById<TextView>(R.id.event)
        val eventTimeTextView = findViewById<TextView>(R.id.time)
        val eventDateTextView = findViewById<TextView>(R.id.date)
        val eventVenueTextView = findViewById<TextView>(R.id.venu)

        groomNameTextView.text = groomName
        brideNameTextView.text = brideName
        eventNameTextView.text = eventName
        eventTimeTextView.text = eventTime
        eventDateTextView.text = eventDate
        eventVenueTextView.text = eventVenue

        // Find the Save button and set a click listener if the button exists in the layout
        val saveButton = findViewById<ImageButton>(R.id.button2)
        saveButton?.setOnClickListener {
            saveCurrentLayoutAsImage()
        } ?: run {
            Toast.makeText(this, "Save button not found in this layout.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveCurrentLayoutAsImage() {
        // Capture the current layout view
        val rootView = findViewById<View>(android.R.id.content).rootView
        val bitmap = captureLayout(rootView)

        // Define the file path and name in the Downloads directory
        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val filePath = File(downloadDir, "wedding_card_layout_${currentLayoutId}.png")

        try {
            val outputStream = FileOutputStream(filePath)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            Toast.makeText(this, "Image saved in Gallery", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error saving image: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun captureLayout(view: View): Bitmap {
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
        return bitmap
    }
}
