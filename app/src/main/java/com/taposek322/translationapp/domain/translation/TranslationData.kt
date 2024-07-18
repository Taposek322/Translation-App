package com.taposek322.translationapp.domain.translation

data class TranslationData(
    val text:String,
    val partOfSpeech: PartOfSpeech,
    val transcription:String,
    val translation:String
)
