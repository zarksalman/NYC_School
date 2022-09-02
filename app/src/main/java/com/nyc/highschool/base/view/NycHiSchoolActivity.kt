package com.nyc.highschool.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nyc.highschool.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NycHiSchoolActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_highschool)
  }


}