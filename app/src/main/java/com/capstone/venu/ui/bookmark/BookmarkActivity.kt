package com.capstone.venu.ui.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.venu.databinding.ActivityBookmarkBinding

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}