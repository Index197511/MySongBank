package me.index197511.mysongbank.di

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import me.index197511.mysongbank.feature.editsong.EditSongRouter
import me.index197511.mysongbank.feature.songlist.SongListRouter
import me.index197511.mysongbank.router.EditSongRouterImpl
import me.index197511.mysongbank.router.SongListRouterImpl

@Module
@InstallIn(FragmentComponent::class)
class RouterModule {
    @Provides
    fun provideNavController(fragment: Fragment): NavController {
        return fragment.findNavController()
    }

    @Provides
    fun provideSongListRouter(impl: SongListRouterImpl): SongListRouter = impl

    @Provides
    fun provideEditSongRouter(impl: EditSongRouterImpl): EditSongRouter = impl
}
