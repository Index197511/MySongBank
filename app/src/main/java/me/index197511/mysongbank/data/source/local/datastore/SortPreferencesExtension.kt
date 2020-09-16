package me.index197511.mysongbank.data.source.local.datastore

import me.index197511.mysongbank.SortOrder
import me.index197511.mysongbank.SortPreferences
import me.index197511.mysongbank.model.SortOption

fun SortPreferences.toSortOption(): SortOption {
    return when (this.sortOrder) {
        SortOrder.BY_ID -> SortOption.ID
        SortOrder.BY_NAME -> SortOption.NAME
        SortOrder.BY_SINGER -> SortOption.SINGER
        SortOrder.BY_KEY -> SortOption.KEY
        else -> SortOption.ID
    }
}