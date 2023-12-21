package com.capstone.venu.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.venu.R
import com.capstone.venu.data.api.mock.ApiConfigMock
import com.capstone.venu.data.response.mock.ArticleDetailMockResponse
import com.capstone.venu.data.response.mock.ArticleListMockResponse
import com.capstone.venu.databinding.ActivityDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val apiServiceMock = ApiConfigMock.getMockApi()
    private lateinit var listData: List<ArticleListMockResponse>

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
                // Call generateListData and get the list of articles
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

    private suspend fun generateListData(): List<ArticleListMockResponse> {
        // Placeholder implementation, replace with your actual API call
        return apiServiceMock.getnewslist()
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
