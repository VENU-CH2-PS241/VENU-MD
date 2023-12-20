package com.capstone.venu.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckRequest(
    @field:SerializedName("hasilCheck") val hasilCheck: String
) : Parcelable