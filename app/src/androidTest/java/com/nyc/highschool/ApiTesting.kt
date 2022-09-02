package com.nyc.highschool

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nyc.highschool.utils.AppConstants

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ApiTesting {
  @Before
  fun setUp() {

  }


  @Test
  fun devUrlTest() {
    val result: Boolean = AppConstants.baseURL == "https://api.themoviedb.org/3/"
    assertTrue(result)
  }
}