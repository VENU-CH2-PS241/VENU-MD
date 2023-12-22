package com.capstone.venu.ui.checker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.venu.data.api.news.ApiConfigNews
import com.capstone.venu.data.response.news.ArticleListNewsResponse
import com.capstone.venu.databinding.ActivityCheckerResultBinding
import com.capstone.venu.ui.detail.DetailAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckerResultActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_RESULT_MESSAGE = "extra_result_message"
        private const val EXTRA_CHECKED_TEXT = "extra_checked_text"

        fun newIntent(context: Context, resultMessage: String, checkedText: String): Intent {
            val intent = Intent(context, CheckerResultActivity::class.java)
            intent.putExtra(EXTRA_RESULT_MESSAGE, resultMessage)
            intent.putExtra(EXTRA_CHECKED_TEXT, checkedText)
            return intent
        }
    }

    private lateinit var binding: ActivityCheckerResultBinding
    private val apiServiceNews = ApiConfigNews.getNewsApi()
    private lateinit var listData: List<ArticleListNewsResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckerResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from intent
        val resultMessage = intent.getStringExtra(EXTRA_RESULT_MESSAGE)
        val checkedText = intent.getStringExtra(EXTRA_CHECKED_TEXT)

        // Display the result
        binding.tvResult.text = resultMessage
        binding.tvNewsPercentage.text = checkedText

        // Set up RecyclerView
        binding.rvListNews.layoutManager = LinearLayoutManager(this)
        binding.rvListNews.setHasFixedSize(true)

        // Fetch data asynchronously using Coroutines
        lifecycleScope.launch(Dispatchers.IO) {
            // Placeholder data for recommended news articles
            listData = generateListData()

            // Set up RecyclerView adapter on the main thread
            launch(Dispatchers.Main) {
                val adapter = DetailAdapter(listData)
                binding.rvListNews.adapter = adapter
            }
        }

        binding.ivBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private suspend fun generateListData(): List<ArticleListNewsResponse> {
        return apiServiceNews.getnewslist()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
