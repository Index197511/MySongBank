package me.index197511.mysongbank.data.repository

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import me.index197511.mysongbank.data.source.local.db.dao.MySongDatabaseDao
import me.index197511.mysongbank.data.source.local.db.entity.SongEntity
import me.index197511.mysongbank.data.test_util.dummySongEntity
import me.index197511.mysongbank.model.Song
import me.index197511.mysongbank.model.test_util.dummySong

class SongRepositoryTest : WordSpec() {
    private lateinit var target: SongRepository

    private val dao: MySongDatabaseDao = mockk()

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        target = SongRepository(dao)
    }

    init {
        "loadAll" When {
            "DB is NOT empty" should {
                val dummySongEntity1 = dummySongEntity(id = 1)
                val dummySongEntity2 = dummySongEntity(id = 2)
                val dummySongEntity3 = dummySongEntity(id = 3)
                val dummySongEntities = listOf(dummySongEntity1, dummySongEntity2, dummySongEntity3)
                val dummyFlow = flow { emit(dummySongEntities) }

                coEvery {
                    dao.getAllSong()
                } returns dummyFlow

                val result = target.loadAll()

                val expectedSong1 = dummySong(id = 1)
                val expectedSong2 = dummySong(id = 2)
                val expectedSong3 = dummySong(id = 3)
                val expectedSongs = listOf(expectedSong1, expectedSong2, expectedSong3)
                val expected = flow { emit(expectedSongs) }

                result.firstOrNull() shouldBe expected.firstOrNull()
            }

            "DB is empty" should {
                val dummyFlow = flow { emit(emptyList<SongEntity>()) }

                coEvery {
                    dao.getAllSong()
                } returns dummyFlow

                val result = target.loadAll()

                val expected = flow { emit(emptyList<Song>()) }
                result.firstOrNull() shouldBe expected.firstOrNull()
            }
        }

        "loadSongWithQuery" When {
            "DB is NOT empty" should {
                val dummySongEntity1 = dummySongEntity(id = 1, name = "AAAAA")
                val dummySongEntity3 = dummySongEntity(id = 3, name = "CCACC")
                val dummySongEntities = listOf(dummySongEntity1, dummySongEntity3)
                val dummyFlow = flow { emit(dummySongEntities) }

                coEvery {
                    dao.getSongsWithQuery("A")
                } returns dummyFlow

                val result = target.loadSongsWithQuery("A")

                val expectedSong1 = dummySong(id = 1, name = "AAAAA")
                val expectedSong3 = dummySong(id = 3, name = "CCACC")
                val expectedSongs = listOf(expectedSong1, expectedSong3)
                val expected = flow { emit(expectedSongs) }

                result.firstOrNull() shouldBe expected.firstOrNull()
            }
            "DB is empty" should {
                val dummyFlow = flow { emit(emptyList<SongEntity>()) }

                coEvery {
                    dao.getSongsWithQuery("AAA")
                } returns dummyFlow

                val result = target.loadSongsWithQuery("AAA")

                val expected = flow { emit(emptyList<Song>()) }
                result.firstOrNull() shouldBe expected.firstOrNull()
            }
            "Query is empty" should {
                val dummySongEntity1 = dummySongEntity(id = 1, name = "AAAAA")
                val dummySongEntity3 = dummySongEntity(id = 3, name = "CCACC")
                val dummySongEntities = listOf(dummySongEntity1, dummySongEntity3)
                val dummyFlow = flow { emit(dummySongEntities) }

                coEvery {
                    dao.getSongsWithQuery("")
                } returns dummyFlow

                val result = target.loadSongsWithQuery("")

                val expectedSong1 = dummySong(id = 1, name = "AAAAA")
                val expectedSong3 = dummySong(id = 3, name = "CCACC")
                val expectedSongs = listOf(expectedSong1, expectedSong3)
                val expected = flow { emit(expectedSongs) }

                result.firstOrNull() shouldBe expected.firstOrNull()
            }
        }
    }
}