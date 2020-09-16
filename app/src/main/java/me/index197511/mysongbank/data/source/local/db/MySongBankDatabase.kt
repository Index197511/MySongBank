package me.index197511.mysongbank.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.index197511.mysongbank.data.source.local.db.dao.MySongDatabaseDao
import me.index197511.mysongbank.data.source.local.db.entity.SongEntity

@Database(entities = [SongEntity::class], version = 1, exportSchema = false)
abstract class MySongBankDatabase : RoomDatabase() {
    abstract fun mySongDatabaseDao(): MySongDatabaseDao
}