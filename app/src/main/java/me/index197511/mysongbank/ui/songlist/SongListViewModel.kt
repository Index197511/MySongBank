package me.index197511.mysongbank.ui.songlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import me.index197511.mysongbank.SortOrder
import me.index197511.mysongbank.SortPreferences
import me.index197511.mysongbank.data.repository.SongRepository
import me.index197511.mysongbank.data.repository.SortPreferencesRepository
import me.index197511.mysongbank.model.Song

class SongListViewModel @ViewModelInject constructor(
    private val songRepository: SongRepository,
    private val sortPrefsRepository: SortPreferencesRepository
) : ViewModel() {
    private val _songs: Flow<List<Song>> = songRepository.loadAll().distinctUntilChanged()
    private val sortOrder: Flow<SortPreferences> = sortPrefsRepository.sortPreferencesFlow
    private val _sortedSongs: Flow<List<Song>> = combine(
        _songs,
        sortOrder
    ) { _songs: List<Song>, sortOrder: SortPreferences ->
        return@combine when (sortOrder.sortOrder) {
            SortOrder.BY_ADDITION -> _songs.sortedBy { it.id }
            SortOrder.BY_NAME -> _songs.sortedBy { it.name }
            SortOrder.BY_SINGER -> _songs.sortedBy { it.singer }
            SortOrder.BY_KEY -> _songs.sortedBy { it.key }
            else -> _songs
        }
    }
    val sortedSongs = _sortedSongs.asLiveData()

    fun removeSong(song: Song) {
        viewModelScope.launch {
            songRepository.remove(song)
        }
    }

    fun insertSong(song: Song) {
        viewModelScope.launch {
            songRepository.add(song)
        }
    }

    fun switchSortOption(key: String) {
        viewModelScope.launch {
            when (key) {
                "ID" -> sortPrefsRepository.enableSortByAddition()
                "NAME" -> sortPrefsRepository.enableSortBySongsName()
                "SINGER" -> sortPrefsRepository.enableSortBySingersName()
                "KEY" -> sortPrefsRepository.enableSortByKey()
            }
        }
    }
}