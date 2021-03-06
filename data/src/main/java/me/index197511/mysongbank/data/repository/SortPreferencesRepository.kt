package me.index197511.mysongbank.data.repository

import android.app.Application
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import kotlinx.coroutines.flow.catch
import me.index197511.mysongbank.SortOrder
import me.index197511.mysongbank.SortPreferences
import me.index197511.mysongbank.data.source.local.datastore.SortPreferencesSerializer
import java.io.IOException
import javax.inject.Inject

private const val SORT_PREFS_FILE = "sort_prefs.pb"

class SortPreferencesRepository @Inject constructor(application: Application) {
    private val sortPreferencesStore: DataStore<SortPreferences> =
        application.applicationContext.createDataStore(
            fileName = SORT_PREFS_FILE,
            serializer = SortPreferencesSerializer
        )

    val sortPreferencesFlow = sortPreferencesStore.data
        .catch { e ->
            if (e is IOException) emit(SortPreferences.getDefaultInstance())
            else throw e
        }

    suspend fun enableSortBy(order: SortOrder) {
        sortPreferencesStore.updateData { currentPreferences ->
            currentPreferences.toBuilder().setSortOrder(order).build()
        }
    }
}