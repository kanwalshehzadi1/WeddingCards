package com.example.weddingcards

import CustomPagerAdapter
import ZoomOutPageTransformer
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.weddingcards.DetailActivity
import com.example.weddingcards.R
import com.example.weddingcards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)

        // Sample data for the ViewPager
        val items = listOf(
            R.drawable.bg1,
            R.drawable.bg2,
            R.drawable.bg3,
            R.drawable.bg4,
            R.drawable.bg5,
        )


        // Set up the adapter with a click listener
        val adapter = CustomPagerAdapter(items) { position ->
            // Start the DataEntryActivity and pass the clicked position
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("clicked_position", position)
            startActivity(intent)
        }
        viewPager.adapter = adapter

        // Set the PageTransformer
        viewPager.setPageTransformer(ZoomOutPageTransformer())
    }
}
