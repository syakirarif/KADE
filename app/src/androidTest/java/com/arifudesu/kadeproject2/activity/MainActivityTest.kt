package com.arifudesu.kadeproject2.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.arifudesu.kadeproject2.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(Main2Activity::class.java)

    @Test
    fun testPastEvent(){

        //menunggu 5 detik
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //klik menu toolbar "PAST EVENT"
        onView(withText("PAST EVENT"))
            .perform(click())

        //memastikan bahwa rv_list_past sudah ditampilkan
        onView(withId(rv_list_past))
            .check(matches(isDisplayed()))

        //scroll rv_list_past ke posisi 8
        onView(withId(rv_list_past))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))

        //klik item di posisi 8
        onView(withId(rv_list_past))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))

        //menunggu 5 detik
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //memastikan bahwa menu dengan id: add_to_favorite sudah ditampilkan
        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))

        //klik menu add_to_favorite
        onView(withId(add_to_favorite)).perform(click())

        //kembali ke activity sebelumnya.
        pressBack()

    }


    @Test
    fun testFavorite(){

        //memastikan bahwa toolbar dengan id: tab_layout sudah ditampilkan
        onView(withId(tab_layout))
            .check(matches(isDisplayed()))

        //klik menu toolbar "FAVORITE"
        onView(withText("FAVORITE"))
            .perform(click())

        //menunggu 5 detik
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //memastikan bahwa rv_list_favorite sudah ditampilkan
        onView(withId(rv_list_favorite))
            .check(matches(isDisplayed()))

        //klik item di posisi 0
        onView(withId(rv_list_favorite))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        //menunggu 5 detik
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //memastikan bahwa menu dengan id: add_to_favorite sudah ditampilkan
        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))

        //klik menu add_to_favorite
        onView(withId(add_to_favorite)).perform(click())

        //kembali ke activity sebelumnya.
        pressBack()

    }


    @Test
    fun testNextEvent(){

        //memastikan bahwa toolbar dengan id: tab_layout sudah ditampilkan
        onView(withId(tab_layout))
            .check(matches(isDisplayed()))

        //klik menu toolbar "NEXT EVENT"
        onView(withText("NEXT EVENT"))
            .perform(click())

        //menunggu 5 detik
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //memastikan bahwa rv_list_next sudah ditampilkan
        onView(withId(rv_list_next))
            .check(matches(isDisplayed()))

        //scroll rv_list_next ke posisi 10
        onView(withId(rv_list_next))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        //klik item di posisi 10
        onView(withId(rv_list_next))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        //menunggu 5 detik
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //memastikan bahwa menu dengan id: add_to_favorite sudah ditampilkan
        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))

        //klik menu add_to_favorite
        onView(withId(add_to_favorite)).perform(click())

        //kembali ke activity sebelumnya.
        pressBack()

    }
}