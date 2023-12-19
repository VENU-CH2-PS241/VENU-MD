package com.capstone.venu.data

import org.checkerframework.checker.units.qual.A

data class CategoriesResponse(
	val data: List<CategoriesDataItem>
)

data class CategoriesAttributes(
	val createdAt: String,
	val publishedAt: String,
	val topic: String,
	val updatedAt: String
)

data class CategoriesDataItem(
	val attributes: CategoriesAttributes,
	val id: Int
)

