package com.taposek322.translationapp.data.remote.skyengApi.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meaning(
    val partOfSpeechCode:String,
    val translation: Translation,
    val transcription:String
)
