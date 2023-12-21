package com.capstone.venu.ui.feedback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.venu.R
import com.capstone.venu.databinding.ActivityFeedbackBinding

class FeedbackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedbackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}