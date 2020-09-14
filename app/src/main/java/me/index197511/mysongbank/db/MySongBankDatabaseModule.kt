package me.index197511.mysongbank.db

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.index197511.mysongbank.db.dao.MySongDatabaseDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MySongBankDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): MySongBankDatabase {
        return Room.databaseBuilder(application, MySongBankDatabase::class.java, "song-database")
            .build()
    }

    @Provides
    @Singleton
    fun provideSongDao(database: MySongBankDatabase): MySongDatabaseDao {
        return database.mySongDatabaseDao()
    }
}