package me.index197511.mysongbank.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.index197511.mysongbank.db.dao.MySongDatabaseDao
import me.index197511.mysongbank.db.entity.SongEntity

@Database(entities = [SongEntity::class], version = 1, exportSchema = false)
abstract class MySongBankDatabase : RoomDatabase() {
    abstract fun mySongDatabaseDao(): MySongDatabaseDao
}