package com.capstone.venu.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.venu.R
import com.capstone.venu.data.api.news.ApiConfigNews
import com.capstone.venu.data.response.news.ArticleDetailNewsResponse
import com.capstone.venu.data.response.news.ArticleListNewsResponse
import com.capstone.venu.databinding.ActivityDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val apiServiceNews = ApiConfigNews.getNewsApi()
    private lateinit var listData: List<ArticleListNewsResponse>

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

        lifecycleScope.launch {
            try {
                listData = generateListData()

                // RecyclerView and adapter
                val recyclerView: RecyclerView = binding.rvListNews
                recyclerView.layoutManager = LinearLayoutManager(this@DetailActivity)
                val adapter = DetailAdapter(listData)
                recyclerView.adapter = adapter

                // FAB click
                val fab: FloatingActionButton = binding.btnTopic
                fab.setOnClickListener {
                    showBottomSheet()
                }
            } catch (e: HttpException) {
                Log.e("HomeFragment", "Error: ${e.message}")
            }
        }
    }

    private suspend fun generateListData(): List<ArticleListNewsResponse> {
        return apiServiceNews.getnewslist()
    }

    private fun loadArticleDetail(articleId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val detailResponse = apiServiceNews.getnewsdetail(articleId)
                displayArticleDetail(detailResponse)
            } catch (e: Exception) {
                e.printStackTrace()
                showToast("Error loading article details: ${e.message}")
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this@DetailActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun displayArticleDetail(detail: ArticleDetailNewsResponse) {
        with(binding) {
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

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_menu, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetDialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
