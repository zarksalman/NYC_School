package com.nyc.highschool

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.nyc.highschool.base.view.NycHiSchoolActivity
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UiTesting {
  @get:Rule
  var activityRule: ActivityTestRule<NycHiSchoolActivity> = ActivityTestRule<NycHiSchoolActivity>(
    NycHiSchoolActivity::class.java,
    true,  // initialTouchMode
    false
  ) // launchActivity. False to customize the intent


  @Test
  fun intent() {
    val intent = Intent()

    activityRule.launchActivity(intent)

    // Continue with your test
  }

  @Test
  fun recyclerTest() {

    var firstActivity: IntentsTestRule<NycHiSchoolActivity> = IntentsTestRule(NycHiSchoolActivity::class.java)
    firstActivity.launchActivity(Intent())

    onView(isRoot()).perform(waitFor(2000))

    onView(withId(R.id.rv_movie_list)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        2,
        click()
      )
    )
  }

  fun waitFor(delay: Long): ViewAction {
    return object : ViewAction {
      override fun perform(uiController: UiController?, view: View?) {
        uiController?.loopMainThreadForAtLeast(delay)
      }

      override fun getConstraints(): Matcher<View> = isRoot()

      override fun getDescription(): String {
        return "wait for " + delay + "milliseconds"
      }
    }
  }
}