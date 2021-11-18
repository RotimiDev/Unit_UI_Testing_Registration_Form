package com.example.week_5_task_regform_ui_testing

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    companion object {
        const val FIRST_NAME = "Rotimi"
        const val LAST_NAME = "Akeem"
        const val EMAIL = "terrymay@gmail.com"
        const val PHONE = "+2348134576849"
        const val SEX = "Male"
        const val MESSAGE = "Mandatory Fields Required"
    }

    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    //Test main activity visibility
    @Test
    fun testActivityInView() {
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()))
    }

    //Firstname visibility
    @Test
    fun testFirstNameEditTextInView() {
        onView(withId(R.id.firstName)).check(matches(isDisplayed()))
    }

    //Test for button visibility
    @Test
    fun testRegisterButtonInView() {
        onView(withId(R.id.btRegister)).check(matches(isDisplayed()))
    }

    //Test spinner visibility
    @Test
    fun testSpinnerIsDisplayed() {
        onView(withId(R.id.spinner)).check(matches(isDisplayed()))
    }


    //Navigation to profile activity test
    @Test
    fun testNavigationToProfileActivity() {
        //Data
        onView(withId(R.id.firstName)).perform(replaceText(FIRST_NAME))
        closeSoftKeyboard()
        onView(withId(R.id.lastName)).perform(replaceText(LAST_NAME))
        closeSoftKeyboard()
        onView(withId(R.id.emailAddress)).perform(replaceText(EMAIL))
        closeSoftKeyboard()
        onView(withId(R.id.phoneNumber)).perform(replaceText(PHONE))
        closeSoftKeyboard()
        Thread.sleep(250)
        //Spinner Item selection
        onView(withId(R.id.spinner)).perform(click())
        onView(withText(SEX)).perform(click());
        //Register button
        onView(withId(R.id.btRegister)).perform(swipeUp(), click())
        //Profile activity displayed check
        onView(withId(R.id.activity_profile)).check(matches(isDisplayed()))
    }

    //Second navigation test, from profile activity back to main activity
    @Test
    fun testNavigationFromProfileActivity_BackPressedToMainActivity() {

        onView(withId(R.id.firstName)).perform(replaceText(FIRST_NAME))
        closeSoftKeyboard()
        onView(withId(R.id.lastName)).perform(replaceText(LAST_NAME))
        closeSoftKeyboard()
        onView(withId(R.id.emailAddress)).perform(replaceText(EMAIL))
        closeSoftKeyboard()
        onView(withId(R.id.phoneNumber)).perform(replaceText(PHONE))
        closeSoftKeyboard()
        Thread.sleep(250)

        onView(withId(R.id.spinner)).perform(click())
        onView(withText(SEX)).perform(click());
        onView(withId(R.id.btRegister)).perform(swipeUp(), click())
        onView(withId(R.id.activity_profile)).check(matches(isDisplayed()))

        //Back press function
        Espresso.pressBack()
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()))
    }

    //Mocking Intent Extras
    @Test
    fun testMainActivityDataSent_vsProfileActivityDataDisplayed() {
        /*Input Data*/
        onView(withId(R.id.firstName)).perform(replaceText(FIRST_NAME))
        closeSoftKeyboard()
        onView(withId(R.id.lastName)).perform(replaceText(LAST_NAME))
        closeSoftKeyboard()
        onView(withId(R.id.emailAddress)).perform(replaceText(EMAIL))
        closeSoftKeyboard()
        onView(withId(R.id.phoneNumber)).perform(replaceText(PHONE))
        closeSoftKeyboard()
        Thread.sleep(250)
        /*Select Spinner Item*/
        onView(withId(R.id.spinner)).perform(click())
        onView(withText(SEX)).perform(click());
        /*Click Register Button*/
        onView(withId(R.id.btRegister)).perform(swipeUp(), click())
        /*Check if Profile Activity is Displayed*/
        onView(withId(R.id.activity_profile)).check(matches(isDisplayed()))
        /*Check if Data Sent from Main Activity matches Data Displayed in Profile*/
        onView(withId(R.id.tvEmail)).check(matches(withText(EMAIL)))
    }
}
