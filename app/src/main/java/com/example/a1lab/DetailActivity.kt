package com.example.a1lab

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvDay = findViewById<TextView>(R.id.tvDetailDay)
        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvDescription = findViewById<TextView>(R.id.tvDetailDescription)
        val ivImage = findViewById<ImageView>(R.id.ivDetailImage)

        val day = intent.getIntExtra("day", 1)
        val titleResId = intent.getIntExtra("titleResId", 0)
        val fullDescriptionResId = intent.getIntExtra("fullDescriptionResId", 0)
        val imageResId = intent.getIntExtra("imageResId", 0)

        tvDay.text = getString(R.string.day_number, day)

        if (titleResId != 0) {
            tvTitle.setText(titleResId)
        }

        if (fullDescriptionResId != 0) {
            tvDescription.setText(fullDescriptionResId)
        }

        if (imageResId != 0) {
            ivImage.setImageResource(imageResId)
        }
    }
}