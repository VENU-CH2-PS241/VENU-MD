package com.capstone.venu.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.venu.R
import com.capstone.venu.data.api.ml.ApiConfigML
import com.capstone.venu.data.api.news.ApiConfigNews
import com.capstone.venu.data.response.home.ArticleResponse
import com.capstone.venu.data.response.mock.ArticleListMockResponse
import com.capstone.venu.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val apiServiceNews = ApiConfigNews.getNewsApi()
    private lateinit var listData: List<ArticleListMockResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use lifecycleScope.launch to launch a coroutine
        lifecycleScope.launch {
            try {
                // Call generateListData and get the list of articles
                listData = generateListData()

                // RecyclerView and adapter
                val recyclerView: RecyclerView = binding.rvListNews
                recyclerView.layoutManager = LinearLayoutManager(context)
                val adapter = HomeAdapter(listData)
                recyclerView.adapter = adapter

                // FAB click
                val fab: FloatingActionButton = binding.btnTopic
                fab.setOnClickListener {
                    showBottomSheet()
                }
            } catch (e: HttpException) {
                Log.e("HomeFragment", "Error: ${e.message}")
                // For example, you can show an error message to the user
            }
        }
    }

    private suspend fun generateListData(): List<ArticleListMockResponse> {
        // Placeholder implementation, replace with your actual API call
        return apiServiceNews.getnewslist()
    }

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_topic, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetDialog.show()
    }
}
