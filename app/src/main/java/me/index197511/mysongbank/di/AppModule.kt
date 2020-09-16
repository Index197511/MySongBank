package me.index197511.mysongbank.di

import android.app.Application
import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.index197511.mysongbank.MainActivity
import me.index197511.mysongbank.R
import me.index197511.mysongbank.feature.editsong.EditSongRouter
import me.index197511.mysongbank.feature.songlist.SongListRouter
import me.index197511.mysongbank.router.EditSongRouterImpl
import me.index197511.mysongbank.router.SongListRouterImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideNavController(activity: MainActivity): NavController {
        return activity.findNavController(R.id.nav_host_fragment)
    }

    @Provides
    fun provideSongListRouter(impl: SongListRouterImpl): SongListRouter = impl

    @Provides
    fun provideEditSongRouter(impl: EditSongRouterImpl): EditSongRouter = impl
}