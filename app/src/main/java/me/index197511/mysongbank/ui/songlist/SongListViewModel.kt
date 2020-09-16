package me.index197511.mysongbank.ui.songlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.index197511.mysongbank.SortOrder
import me.index197511.mysongbank.SortPreferences
import me.index197511.mysongbank.model.SortOption
import me.index197511.mysongbank.data.source.local.datastore.toSortOption
import me.index197511.mysongbank.model.toSortPreferences
import me.index197511.mysongbank.data.repository.SongRepository
import me.index197511.mysongbank.data.repository.SortPreferencesRepository
import me.index197511.mysongbank.model.Song

@ExperimentalCoroutinesApi
@FlowPreview
class SongListViewModel @ViewModelInject constructor(
    private val songRepository: SongRepository,
    private val sortPrefsRepository: SortPreferencesRepository
) : ViewModel() {
    private val searchChannel = ConflatedBroadcastChannel<String>()
    private var songs: Flow<List<Song>> = searchChannel.asFlow()
        .flatMapLatest { search ->
            songRepository.loadSongsWithQuery(search)
        }
    private val _sortOrder: Flow<SortPreferences> = sortPrefsRepository.sortPreferencesFlow
    private var _sortedSongs: Flow<List<Song>> = combine(
        songs,
        _sortOrder
    ) { _songs: List<Song>, sortOrder: SortPreferences ->
        return@combine when (sortOrder.sortOrder) {
            SortOrder.BY_ID -> _songs.sortedBy { it.id }
            SortOrder.BY_NAME -> _songs.sortedBy { it.name }
            SortOrder.BY_SINGER -> _songs.sortedBy { it.singer }
            SortOrder.BY_KEY -> _songs.sortedBy { it.key }
            else -> _songs
        }
    }
    val sortedSongs = _sortedSongs.asLiveData()
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
        searchChannel.offer(query)
    }
}