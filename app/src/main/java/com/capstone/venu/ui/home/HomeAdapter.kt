package com.capstone.venu.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.venu.data.response.mock.ArticleListMockResponse
import com.capstone.venu.databinding.ItemNewsBinding
import com.capstone.venu.ui.detail.DetailActivity

class HomeAdapter(private val newsList: List<ArticleListMockResponse>) :
    RecyclerView.Adapter<HomeAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size
    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: ArticleListMockResponse) {
            // Trim the title if it has more than 6 words
            val trimmedTitle = trimTitle(newsItem.title, 6)

            binding.tvNewsTitle.text = trimmedTitle
            binding.tvNewsTopic.text = newsItem.topic

            Glide
                .with(itemView.context)
                .load(newsItem.image)
                .into(binding.ivNews)

            binding.root.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("Detail", newsItem.title)
                itemView.context.startActivity(intent)
            }

        }

        private fun trimTitle(title: String, maxWords: Int): String {
            val words = title.split(" ")
            return if (words.size > maxWords) {
                words.subList(0, maxWords).joinToString(" ") + " ..."
            } else {
                title
            }
        }
    }
}