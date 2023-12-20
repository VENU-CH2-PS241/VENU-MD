/*
package com.capstone.venu.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.venu.data.local.dummy.NewsItem
import com.capstone.venu.data.response.mock.ArticleListMockResponse
import com.capstone.venu.databinding.ItemNewsBinding

class NewsAdapter(private val newsList: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: ArticleListMockResponse) {
            // Bind data to the views
            binding.ivNews.setImageResource(newsItem.image)
            binding.tvNewsTitle.text = newsItem.title
            binding.tvNewsPercentage.text = newsItem.percentage
            binding.tvNewsTopic.text = newsItem.topic
        }
    }
}*/
