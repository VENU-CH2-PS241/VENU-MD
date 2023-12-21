package com.capstone.venu.ui.faq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.venu.R
import com.capstone.venu.databinding.ActivityFaqBinding

class FaqActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFaqBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}