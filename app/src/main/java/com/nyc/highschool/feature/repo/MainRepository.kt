package com.nyc.highschool.feature.repo

import com.nyc.highschool.base.ApiHelper


import com.nyc.highschool.utils.AppConstants
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val apiHelper: ApiHelper
) {
  var TAG = "MovieRepository"
  suspend fun getNycHighSchoolListObserverRx(query: String) =
    apiHelper.getNycHighSchoolList(query, AppConstants.API_TOKEN)

  suspend fun getSatScoreObserverRx(query: Int) =
    apiHelper.getSatScore(query, AppConstants.API_TOKEN)

}
