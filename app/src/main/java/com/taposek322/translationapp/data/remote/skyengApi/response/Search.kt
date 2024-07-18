package com.taposek322.translationapp.data.remote.skyengApi.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Search(
    val text:String,
    val meanings: List<Meaning>,
)
