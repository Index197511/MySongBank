package me.index197511.mysongbank.feature.songlist

import me.index197511.mysongbank.model.Song

interface SongListRouter {
    fun navToEditSongFragment(song: Song)
}