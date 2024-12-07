package com.example.moapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class InfoItemActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.infoitemactivitylayout)
        val image = intent.getIntExtra("imageid",R.drawable.defaultimage)
        val name = intent.getStringExtra("name")
        val detail = intent.getStringExtra("detail")

        val imageView = findViewById<ImageView>(R.id.infoimage)
        val nameView = findViewById<TextView>(R.id.infotitle)
        val detailView = findViewById<TextView>(R.id.infodetail)

        imageView.setImageResource(image)
        nameView.text = name
        detailView.text = detail

    }
}
