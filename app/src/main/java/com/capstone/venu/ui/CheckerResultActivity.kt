package com.capstone.venu.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.venu.R
import com.capstone.venu.data.api.ml.ApiConfigML
import com.capstone.venu.databinding.FragmentCheckerResultBinding
import com.capstone.venu.ui.checker.CheckerAdapter

class CheckerResultActivity : AppCompatActivity() {
    private lateinit var binding: FragmentCheckerResultBinding
    private val apiServiceML = ApiConfigML.getApiML()
    private lateinit var adapter: CheckerAdapter // Replace NewsListAdapter with your actual adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCheckerResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvResult.text = getString(R.string.lorem_ipsum)
        binding.tvRecommend.text = getString(R.string.recommend)

        val recyclerView: RecyclerView = binding.rvListNews
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CheckerAdapter() // Replace NewsListAdapter with your actual adapter
    }
}
