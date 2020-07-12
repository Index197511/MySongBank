package me.index197511.mysongbank.ui.editsong


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import me.index197511.mysongbank.MainActivity
import me.index197511.mysongbank.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class EditSongFragmentInstrumentedUITest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun editSongFragmentInstrumentedUITest() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.button_add_new_song),
                childAtPosition(
                    allOf(
                        withId(R.id.song_list_base),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        runBlocking { delay(1000L) }

        val appCompatEditText = onView(
            allOf(
                withId(R.id.edit_text_name),
                childAtPosition(
                    allOf(
                        withId(R.id.insert_new_song_base),
                        childAtPosition(
                            withId(R.id.md_content_layout),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("BeforeName"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.edit_text_singer),
                childAtPosition(
                    allOf(
                        withId(R.id.insert_new_song_base),
                        childAtPosition(
                            withId(R.id.md_content_layout),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("BeforeSinger"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.edit_text_key),
                childAtPosition(
                    allOf(
                        withId(R.id.insert_new_song_base),
                        childAtPosition(
                            withId(R.id.md_content_layout),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("6"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.edit_text_memo),
                childAtPosition(
                    allOf(
                        withId(R.id.insert_new_song_base),
                        childAtPosition(
                            withId(R.id.md_content_layout),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("Memo"), closeSoftKeyboard())

        val dialogActionButton = onView(
            allOf(
                withId(R.id.md_button_positive), withText("OK"),
                childAtPosition(
                    allOf(
                        withId(R.id.md_button_layout),
                        childAtPosition(
                            withClassName(`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        dialogActionButton.perform(click())

        runBlocking { delay(1000L) }

        val appCompatImageButton = onView(
            allOf(
                withId(R.id.button_edit), withContentDescription("edit"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.song_list_item_root),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        runBlocking { delay(1000L) }

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.edit_text_name), withText("BeforeName"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_song_base),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("AfterName"))

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.edit_text_name), withText("AfterName"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_song_base),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(closeSoftKeyboard())

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.edit_text_singer), withText("BeforeSinger"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_song_base),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText7.perform(replaceText("AfterSinger"))

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.edit_text_singer), withText("AfterSinger"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_song_base),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText8.perform(closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.button_save), withText("Save"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_song_base),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        runBlocking { delay(1000L) }

        val recyclerView = onView(
            allOf(
                withId(R.id.recycler_view_song_list),
                childAtPosition(
                    withId(R.id.song_list_base),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        runBlocking { delay(1000L) }

        val dialogActionButton2 = onView(
            allOf(
                withId(R.id.md_button_negative), withText("Cancel"),
                childAtPosition(
                    allOf(
                        withId(R.id.md_button_layout),
                        childAtPosition(
                            withClassName(`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                            1
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        dialogActionButton2.perform(click())

        runBlocking { delay(1000L) }

        val recyclerView2 = onView(
            allOf(
                withId(R.id.recycler_view_song_list),
                childAtPosition(
                    withId(R.id.song_list_base),
                    0
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, longClick()))

        runBlocking { delay(1000L) }

        val dialogActionButton3 = onView(
            allOf(
                withId(R.id.md_button_positive), withText("OK"),
                childAtPosition(
                    allOf(
                        withId(R.id.md_button_layout),
                        childAtPosition(
                            withId(R.id.md_root),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        dialogActionButton3.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
