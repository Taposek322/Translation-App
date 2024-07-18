package com.taposek322.translationapp.domain.dbRepository

import com.taposek322.translationapp.domain.dbData.HistoryData

interface DataBaseRepository {
    suspend fun getAllTranslation():List<HistoryData>

    suspend fun getFavouriteTranslation():List<HistoryData>

    suspend fun insertTranslation(historyData: HistoryData)

    suspend fun updateTranslation(historyData: HistoryData)

    suspend fun deleteTranslation(historyData: HistoryData)

    suspend fun findTranslation(text:String):Boolean
}