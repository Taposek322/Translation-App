package com.taposek322.translationapp.data.db.repository

import com.taposek322.translationapp.data.db.mapper.toHistoryData
import com.taposek322.translationapp.data.db.mapper.toTranslationEntity
import com.taposek322.translationapp.data.db.room.TranslationHistoryDB
import com.taposek322.translationapp.domain.dbData.HistoryData
import com.taposek322.translationapp.domain.dbRepository.DataBaseRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataBaseRepositoryImpl @Inject constructor(
    private val translationHistoryDB: TranslationHistoryDB
): DataBaseRepository {
    override suspend fun getAllTranslation(): List<HistoryData> {
        return translationHistoryDB.translationDao().getAllTranslation().map {
            it.toHistoryData()
        }
    }

    override suspend fun getFavouriteTranslation(): List<HistoryData> {
        return translationHistoryDB.translationDao().getFavouriteTranslation().map {
            it.toHistoryData()
        }
    }

    override suspend fun insertTranslation(historyData: HistoryData) {
        translationHistoryDB.translationDao().insertTranslation(historyData.toTranslationEntity())
    }

    override suspend fun updateTranslation(historyData: HistoryData) {
        translationHistoryDB.translationDao().updateTranslation(historyData.toTranslationEntity())
    }

    override suspend fun deleteTranslation(historyData: HistoryData) {
        translationHistoryDB.translationDao().deleteTranslation(historyData.toTranslationEntity())
    }

    override suspend fun findTranslation(text: String):Boolean {
        return translationHistoryDB.translationDao().findTranslation(text) != null
    }
}