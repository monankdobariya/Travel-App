package com.example.travelapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapp.R
import com.example.travelapp.databinding.ActivityIntroduction2Binding
import com.example.travelapp.databinding.ActivityIntroduction3Binding

class Introduction3Activity : AppCompatActivity() {
    lateinit var  binding: ActivityIntroduction3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroduction3Binding.inflate(layoutInflater)

        setContentView(binding.root)
        initview()
    }

    private fun initview() {
        binding.cardNext3.setOnClickListener {
            var  i = Intent(this, LoginActivity::class.java)
            startActivity(i)

        }

    }
}