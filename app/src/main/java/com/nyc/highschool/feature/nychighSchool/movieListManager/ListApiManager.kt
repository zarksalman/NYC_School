package com.nyc.highschool.feature.nychighSchool.movieListManager


import com.nyc.highschool.base.Filter
import com.nyc.highschool.utils.AppConstants

class ListApiManager {

  fun nychighSchoolList(popularMovies: PopularNycHighSchool) {
    popularMovies.getNycHighSchool(Filter.UPCOMING.filter, AppConstants.API_TOKEN)
  }

}