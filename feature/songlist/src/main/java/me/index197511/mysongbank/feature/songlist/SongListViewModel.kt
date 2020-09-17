package me.index197511.mysongbank.feature.songlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.index197511.mysongbank.SortOrder
import me.index197511.mysongbank.SortPreferences
import me.index197511.mysongbank.data.repository.SongRepository
import me.index197511.mysongbank.data.repository.SortPreferencesRepository
import me.index197511.mysongbank.data.source.local.datastore.SortOption
import me.index197511.mysongbank.data.source.local.datastore.toSortOption
import me.index197511.mysongbank.data.source.local.datastore.toSortPreferences
import me.index197511.mysongbank.model.Song

@ExperimentalCoroutinesApi
@FlowPreview
class SongListViewModel @ViewModelInject constructor(
    private val songRepository: SongRepository,
    private val sortPrefsRepository: SortPreferencesRepository
) : ViewModel() {
    private val _searchFlow = MutableStateFlow("")
    private var _songs: Flow<List<Song>> = _searchFlow
        .flatMapLatest { search ->
            songRepository.loadSongsWithQuery(search)
        }
    private val _sortOrder: Flow<SortPreferences> = sortPrefsRepository.sortPreferencesFlow

    val sortedSongs = combine(
        _songs,
        _sortOrder
    ) { _songs: List<Song>, sortOrder: SortPreferences ->
        return@combine when (sortOrder.sortOrder) {
            SortOrder.BY_ID -> _songs.sortedBy { it.id }
            SortOrder.BY_NAME -> _songs.sortedBy { it.name }
            SortOrder.BY_SINGER -> _songs.sortedBy { it.singer }
            SortOrder.BY_KEY -> _songs.sortedBy { it.key }
            else -> _songs
        }
    }.asLiveData()

    val sortOrder = _sortOrder.map { it.toSortOption() }.asLiveData()

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

    fun switchSortOption(option: SortOption) {
        viewModelScope.launch {
            sortPrefsRepository.enableSortBy(option.toSortPreferences())
        }
    }

    fun filterWithQuery(query: String) {
        _searchFlow.value = query
    }
}