package me.index197511.mysongbank.router

import androidx.navigation.NavController
import me.index197511.mysongbank.feature.editsong.EditSongRouter
import me.index197511.mysongbank.feature.songlist.SongListFragmentDirections
import me.index197511.mysongbank.feature.songlist.SongListRouter
import me.index197511.mysongbank.model.Song
import javax.inject.Inject

class SongListRouterImpl @Inject constructor(
    private val controller: NavController
) : SongListRouter {
    override fun navToEditSongFragment(song: Song) {
        controller.navigate(SongListFragmentDirections.actionSongListFragmentToEditSongFragment(song))
    }
}

class EditSongRouterImpl @Inject constructor(private val controller: NavController) :
    EditSongRouter {
    override fun navToSongListFragment() {
        controller.navigate(R.id.action_editSongFragment_to_songListFragment)
    }
}
