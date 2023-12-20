package com.capstone.venu.data.temp

data class RecomendationsResponse(
	val recomendationsResponse: List<RecomendationsResponseItem>
)

data class RecomendationsResponseItem(
	val image: String,
	val description: String,
	val topic: String,
	val source: String,
	val id: String,
	val title: String
)

