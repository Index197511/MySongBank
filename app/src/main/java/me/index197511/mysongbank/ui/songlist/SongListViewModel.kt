package me.index197511.mysongbank.ui.songlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import me.index197511.mysongbank.data.SongRepository
import me.index197511.mysongbank.model.Song

class SongListViewModel @ViewModelInject constructor(
    private val repository: SongRepository
) : ViewModel() {
    @ExperimentalCoroutinesApi
    val allSongs: LiveData<List<Song>> =
        repository.loadAll().distinctUntilChanged().asLiveData()

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