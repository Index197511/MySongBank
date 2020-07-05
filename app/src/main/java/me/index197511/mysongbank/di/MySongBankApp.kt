package me.index197511.mysongbank.di

import android.app.Application
import androidx.room.Room
import me.index197511.mysongbank.db.MySongBankDatabase
import me.index197511.mysongbank.ui.songlist.SongListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Suppress("UNUSED")
class MySongBankApp : Application() {

    private val module = module {
        viewModel { SongListViewModel() }
        single {
            Room.databaseBuilder(get(), MySongBankDatabase::class.java, "song-database").build()
        }
        single { get<MySongBankDatabase>().mySongDatabaseDao() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(module)
        }
    }
}