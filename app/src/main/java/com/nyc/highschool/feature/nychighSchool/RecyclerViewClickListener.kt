package com.nyc.highschool.feature.nychighSchool

import android.view.View
import com.nyc.highschool.feature.nychighSchool.listResponse.BaseListResponse


interface RecyclerViewClickListener {
  fun onRecyclerViewItemClick(view: View, movie: BaseListResponse?)
}