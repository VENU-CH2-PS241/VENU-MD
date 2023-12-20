package com.capstone.venu.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.venu.data.api.mock.ApiConfigMock
import com.capstone.venu.data.response.mock.ArticleDetailMockResponse
import com.capstone.venu.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val apiServiceMock = ApiConfigMock.getMockApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val articleId = intent.getStringExtra("Detail")
        if (articleId != null) {
            loadArticleDetail(articleId)
        }
    }

    private fun loadArticleDetail(articleId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val detailResponse = apiServiceMock.getnewsdetail(articleId)
                displayArticleDetail(detailResponse)
            } catch (e: Exception) {
                // Handle error, for example show a toast
                e.printStackTrace()
            }
        }
    }

    private fun displayArticleDetail(detail: ArticleDetailMockResponse) {
        with(binding) {
            // Populate the UI with the details
            Glide.with(this@DetailActivity)
                .load(detail.image)
                .into(ivDet)

            tvTitledet.text = detail.title
            tvTopicdet.text = detail.topic
            tvSourcedet.text = detail.source
            tvDescdet.text = detail.description

            // Hide the progress bar
            pbDetail.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
