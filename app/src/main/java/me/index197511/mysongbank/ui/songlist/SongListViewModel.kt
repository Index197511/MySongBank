package me.index197511.mysongbank.ui.songlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.index197511.mysongbank.data.SongRepository
import me.index197511.mysongbank.model.Song
import org.koin.core.KoinComponent
import org.koin.core.inject

class SongListViewModel : ViewModel(), KoinComponent {
    private val repository by inject<SongRepository>()

    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>>
        get() {
            return _songs
        }

    init {
        setAllSongs()
    }

    fun removeSong(song: Song) {
        viewModelScope.launch {
            repository.remove(song)
            setAllSongs()
        }
    }

    fun insertSong(song: Song) {
        viewModelScope.launch {
            repository.add(song)
        }
        setAllSongs()
    }

    private fun setAllSongs() {
        viewModelScope.launch {
            _songs.value = repository.loadAll()
        }
    }


}