package com.joblogic.technicaltestandroid

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun TestButtonCallClick_expectCorrectQuote() {
        onView(withId(R.id.btnCall)).perform(click())
        onView(withId(R.id.tvDetail)).check(matches(withText("Call List")))
    }

    @Test
    fun TestButtonBuyClick_expectCorrectQuote() {
        onView(withId(R.id.btnCall)).perform(click())
        onView(withId(R.id.tvDetail)).check(matches(withText("Buy List")))
    }

    @Test
    fun TestButtonSellClick_expectCorrectQuote() {
        onView(withId(R.id.btnCall)).perform(click())
        onView(withId(R.id.tvDetail)).check(matches(withText("Sell List")))
    }
}