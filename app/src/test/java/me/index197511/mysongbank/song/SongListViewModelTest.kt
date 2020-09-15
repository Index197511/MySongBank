package me.index197511.mysongbank.song

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import me.index197511.mysongbank.SortOrder
import me.index197511.mysongbank.SortPreferences
import me.index197511.mysongbank.data.repository.SongRepository
import me.index197511.mysongbank.data.repository.SortPreferencesRepository
import me.index197511.mysongbank.model.Song
import me.index197511.mysongbank.ui.songlist.SongListViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SongListViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var songRepository: SongRepository

    @MockK
    private lateinit var sortPrefsRepository: SortPreferencesRepository

    @MockK
    private lateinit var observer: Observer<List<Song>>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this, true, true)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `insert new song`() {
        val mockCorrectFlow: Flow<List<Song>> = flow {
            listOf(
                Song(id = 1, name = "Song1", singer = "Singer1", key = 0, memo = ""),
                Song(id = 2, name = "Song2", singer = "Singer2", key = 0, memo = ""),
                Song(id = 3, name = "Song3", singer = "Singer3", key = 0, memo = "")
            )
        }

        coEvery { songRepository.loadAll() } returns mockCorrectFlow
        coEvery { songRepository.add(song = any()) } returns Unit

        val mockSortPrefsFlow: Flow<SortPreferences> = flow {
            SortPreferences.getDefaultInstance().toBuilder().setSortOrder(SortOrder.BY_NAME).build()
        }
        coEvery { sortPrefsRepository.sortPreferencesFlow } returns mockSortPrefsFlow

        val viewModel = SongListViewModel(songRepository, sortPrefsRepository)
        viewModel.sortedSongs.observeForever(observer)

        runBlocking {
            viewModel.insertSong(
                Song(
                    id = 1,
                    name = "Song1",
                    singer = "Singer1",
                    key = 0,
                    memo = ""
                )
            )
        }
        runBlocking {
            viewModel.insertSong(
                Song(
                    id = 2,
                    name = "Song2",
                    singer = "Singer2",
                    key = 0,
                    memo = ""
                )
            )
        }
        runBlocking {
            viewModel.insertSong(
                Song(
                    id = 3,
                    name = "Song3",
                    singer = "Singer3",
                    key = 0,
                    memo = ""
                )
            )
        }
        assertThat(viewModel.sortedSongs.value).isEqualTo(mockCorrectFlow.asLiveData().value)
    }
}

