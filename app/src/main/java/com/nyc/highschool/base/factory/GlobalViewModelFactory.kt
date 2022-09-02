package com.nyc.highschool.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyc.highschool.feature.satscore.viewmodel.SatScoreViewModel
import com.nyc.highschool.feature.nychighSchool.viewmodel.NycHighSchoolListViewModel
import com.nyc.highschool.feature.repo.MainRepository
import javax.inject.Inject

class GlobalViewModelFactory @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return when (modelClass) {
      NycHighSchoolListViewModel::class.java -> {
        NycHighSchoolListViewModel(mainRepository) as T
      }
      SatScoreViewModel::class.java -> {
        SatScoreViewModel(mainRepository) as T
      }
      else -> {
        super.create(modelClass)
      }
    }
  }


}