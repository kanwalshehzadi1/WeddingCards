package com.example.weddingcards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.weddingcards.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var clickedPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val groomNameEditText = findViewById<EditText>(R.id.name)
        val brideNameEditText = findViewById<EditText>(R.id.bridename)
        val eventNameEditText = findViewById<EditText>(R.id.functionname)
        val eventTimeEditText = findViewById<EditText>(R.id.functiontime)
        val eventDateEditText = findViewById<EditText>(R.id.functiondate)
        val eventVenueEditText = findViewById<EditText>(R.id.venuname)
        val previewButton = findViewById<Button>(R.id.button)

        // Change background based on selected radio button
        binding.RadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.grooms -> binding.main.setBackgroundResource(R.drawable.groom)
                R.id.bride -> binding.main.setBackgroundResource(R.drawable.bride)
            }
        }

        // Get the clicked position from the intent
        clickedPosition = intent.getIntExtra("clicked_position", 0)

        previewButton.setOnClickListener {
            val groomName = groomNameEditText.text.toString()
            val brideName = brideNameEditText.text.toString()
            val eventName = eventNameEditText.text.toString()
            val eventTime = eventTimeEditText.text.toString()
            val eventDate = eventDateEditText.text.toString()
            val eventVenue = eventVenueEditText.text.toString()

            // Start PreviewActivity and pass the data
            val intent = Intent(this, PreviewActivity::class.java)
            intent.putExtra("clicked_position", clickedPosition)
            intent.putExtra("groom_name", groomName)
            intent.putExtra("bride_name", brideName)
            intent.putExtra("event_name", eventName)
            intent.putExtra("event_time", eventTime)
            intent.putExtra("event_date", eventDate)
            intent.putExtra("event_venue", eventVenue)
            startActivity(intent)
        }
    }
}
