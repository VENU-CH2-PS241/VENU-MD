package com.capstone.venu.ui.checker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.venu.R
import com.capstone.venu.data.local.dummy.NewsItem
import com.capstone.venu.databinding.FragmentCheckerResultBinding


class CheckerResultFragment : Fragment() {
    private lateinit var binding: FragmentCheckerResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckerResultBinding.inflate(layoutInflater)

        binding.tvResult.text = getString(R.string.lorem_ipsum)
        binding.tvRecommend.text = getString(R.string.recommend)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.rvListNews
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}