package com.capstone.venu.data

data class NewsResponse(
	val data: List<NewsDataItem>
)

data class NewsAttributes(
	val newsTitle1: String,
	val createdAt: String,
	val newsTitle2: String,
	val categoryId: String,
	val resource: String,
	val like: String,
	val publishedAt: String,
	val content: String,
	val updatedAt: String
)

data class NewsDataItem(
    val attributes: NewsAttributes,
    val id: Int
)

