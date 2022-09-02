package com.nyc.highschool.feature.nychighSchool.movieListManager

import com.nyc.highschool.feature.nychighSchool.list_api_interface.IPopularNycHighSchool
import com.nyc.highschool.feature.nychighSchool.viewmodel.NycHighSchoolListViewModel

class PopularNycHighSchool(private val nycHighSchoolViewModel: NycHighSchoolListViewModel) : IPopularNycHighSchool {
  override fun getNycHighSchool(query: String, apiToken: String) {
    nycHighSchoolViewModel.prepareNycHighSchoolListRepo(query)
  }


}