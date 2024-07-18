package com.taposek322.translationapp.domain.dbData

import com.taposek322.translationapp.domain.translation.TranslationData

data class HistoryData(
    val id:Long,
    val translationData: TranslationData,
    val favourite:Boolean
)
