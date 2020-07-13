package me.index197511.mysongbank.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import me.index197511.mysongbank.db.dao.MySongDatabaseDao
import me.index197511.mysongbank.db.entity.SongEntity
import me.index197511.mysongbank.db.entity.toEntity
import me.index197511.mysongbank.model.Song
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MySongDatabaseDaoInstrumentedTest {
    private lateinit var dao: MySongDatabaseDao
    private lateinit var db: MySongBankDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(
            context,
            MySongBankDatabase::class.java
        ).build()
        dao = db.mySongDatabaseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testInsertSong() {
        val addedSongs: List<SongEntity> = generateTestData(3)
        runBlocking {
            addedSongs.forEach { song -> dao.insertSong(song) }
        }

        updateSongList().let {
            assertThat(it.size).isEqualTo(3)
            assertThat(it[0].name).isEqualTo("SongName3")
            assertThat(it[1].singer).isEqualTo("Singer2")
            assertThat(it[2].key).isEqualTo(1)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testRemoveSong() {
        runBlocking {
            generateTestData(3).forEach { song -> dao.insertSong(song) }
        }

        val removedSong = runBlocking { dao.getAllSong() }.map { e -> e.toModel() }[1]
        runBlocking { dao.deleteSong(removedSong.toEntity()) }

        updateSongList().let {
            assertThat(it.size).isEqualTo(2)
            assertThat(it).doesNotContain(removedSong)
        }
    }

    private fun updateSongList(): List<Song> {
        return runBlocking { dao.getAllSong() }.map { e -> e.toModel() }
    }

    private fun generateTestData(numOfTestData: Int): List<SongEntity> {
        val testSongs: MutableList<SongEntity> = mutableListOf()
        (1..numOfTestData).forEach { x ->
            val song =
                Song(id = 0, name = "SongName$x", singer = "Singer$x", key = x, memo = "Memo$x")
            testSongs.add(song.toEntity())
        }

        return testSongs.toList()
    }

}