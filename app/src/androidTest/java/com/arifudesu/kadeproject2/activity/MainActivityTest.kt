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
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testPastEvent(){

        //memastikan bahwa toolbar dengan id: btm_navigation sudah ditampilkan
        onView(withId(btm_navigation))
            .check(matches(isDisplayed()))

        //klik menu toolbar "MATCH"
        onView(withId(btm_match))
            .perform(click())

        //menunggu 5 detik
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //klik menu toolbar "PAST EVENT"
        onView(withText("PAST MATCH"))
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

        //memastikan bahwa toolbar dengan id: btm_navigation sudah ditampilkan
        onView(withId(btm_navigation))
            .check(matches(isDisplayed()))

        //klik menu toolbar "FAVORITE"
        onView(withId(btm_favorite))
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

        //memastikan bahwa toolbar dengan id: btm_navigation sudah ditampilkan
        onView(withId(btm_navigation))
            .check(matches(isDisplayed()))

        //klik menu toolbar "MATCH"
        onView(withId(btm_match))
            .perform(click())

        //memastikan bahwa toolbar dengan id: tab_layout sudah ditampilkan
        onView(withId(toolbar_main))
            .check(matches(isDisplayed()))

        //klik menu toolbar "NEXT EVENT"
        onView(withText("NEXT MATCH"))
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

    @Test
    fun testClubTab(){

        //memastikan bahwa toolbar dengan id: btm_navigation sudah ditampilkan
        onView(withId(btm_navigation))
            .check(matches(isDisplayed()))

        //klik menu "CLUB"
        onView(withId(btm_club))
            .perform(click())

        //menunggu 5 detik
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //memastikan bahwa rv_club sudah ditampilkan
        onView(withId(rv_club))
            .check(matches(isDisplayed()))

        //scroll rv_club ke posisi 5
        onView(withId(rv_club))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        //klik item di posisi 5
        onView(withId(rv_club))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

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
    fun testDiscoverTab(){

        //memastikan bahwa toolbar dengan id: btm_navigation sudah ditampilkan
        onView(withId(btm_navigation))
            .check(matches(isDisplayed()))

        //klik menu "DISCOVER"
        onView(withId(btm_discover))
            .perform(click())


    }
}