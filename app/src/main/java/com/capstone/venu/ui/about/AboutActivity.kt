package com.capstone.venu.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.venu.R
import com.capstone.venu.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}