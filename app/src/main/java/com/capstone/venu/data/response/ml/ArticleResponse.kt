    package com.capstone.venu.data.response.ml

    import android.os.Parcelable
    import com.google.gson.annotations.SerializedName
    import kotlinx.parcelize.Parcelize

    @Parcelize
    data class ArticleResponse(
        @field:SerializedName("link")
        val link: String,
        @field:SerializedName("berita")
        val berita: String,
        @field:SerializedName("title")
        val title: String,
        @field:SerializedName("source")
        val source: String,
        @field:SerializedName("tanggal")
        val tanggal: String,
        @field:SerializedName("jenis")
        val jenis: String,
        @field:SerializedName("image")
        val image: String
    ) : Parcelable
