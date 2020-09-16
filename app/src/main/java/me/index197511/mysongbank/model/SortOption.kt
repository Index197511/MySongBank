package me.index197511.mysongbank.model

import me.index197511.mysongbank.SortOrder

enum class SortOption {
    ID,
    NAME,
    SINGER,
    KEY
}

fun SortOption.toSortPreferences(): SortOrder {
    return when (this) {
        SortOption.ID -> SortOrder.BY_ID
        SortOption.NAME -> SortOrder.BY_NAME
        SortOption.SINGER -> SortOrder.BY_SINGER
        SortOption.KEY -> SortOrder.BY_KEY
    }
}