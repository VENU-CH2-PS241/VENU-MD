package com.capstone.venu.ui.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.venu.R
import com.capstone.venu.data.local.dummy.NewsItem
import com.capstone.venu.databinding.ActivityBookmarkBinding
import com.capstone.venu.databinding.ActivitySignInBinding
import com.capstone.venu.ui.adapter.NewsAdapter

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Dummy data
        val dummyData = generateDummyData()

        // RecyclerView and adapter
        val recyclerView: RecyclerView = binding.rvListNews
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NewsAdapter(dummyData)
        recyclerView.adapter = adapter
    }

    private fun generateDummyData(): List<NewsItem> {
        val dummyList = mutableListOf<NewsItem>()

        dummyList.add(NewsItem(R.drawable.img_news, "Sample News Title 1", "80%", "Technology"))
        dummyList.add(NewsItem(R.drawable.img_news, "Sample News Title 2", "65%", "Politics"))
        dummyList.add(NewsItem(R.drawable.img_news, "Sample News Title 3", "90%", "Sports"))

        return dummyList
    }
}