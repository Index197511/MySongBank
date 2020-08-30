package me.index197511.mysongbank.ui.songlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import me.index197511.mysongbank.data.SongRepository
import me.index197511.mysongbank.model.Song
import org.koin.core.KoinComponent
import org.koin.core.inject

class SongListViewModel : ViewModel(), KoinComponent {
    private val repository by inject<SongRepository>()
    @ExperimentalCoroutinesApi
    val songs: LiveData<List<Song>> = repository.loadAll().distinctUntilChanged().asLiveData()

    fun removeSong(song: Song) {
        viewModelScope.launch {
            repository.remove(song)
        }
    }

    fun insertSong(song: Song) {
        viewModelScope.launch {
            repository.add(song)
        }
    }
}