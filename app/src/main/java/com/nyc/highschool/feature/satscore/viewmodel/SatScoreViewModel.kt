package com.nyc.highschool.feature.satscore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nyc.highschool.feature.satscore.satscoreresponse.SatScoreResponse
import com.nyc.highschool.feature.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SatScoreViewModel @Inject constructor(
  private val mainRepository: MainRepository
) : ViewModel() {
  var job: Job? = null
  private val errorMessage = MutableLiveData<String>()
  private val _movieDetailResponse = MutableLiveData<SatScoreResponse>()
  val movieDetailLiveData: MutableLiveData<SatScoreResponse>
    get() = _movieDetailResponse

  fun prepareMovieDetailRepo(name: Int) {
    job = CoroutineScope(Dispatchers.IO).launch {
      val response = mainRepository.getSatScoreObserverRx(name)
      withContext(Dispatchers.Main) {

        if (response.isSuccessful) {

          _movieDetailResponse.postValue(response.body() as SatScoreResponse)
//                    loading.value = false
        } else {
          onError("Error : ${response.message()} ")
        }
      }
    }

  }

  private fun onError(message: String) {
    errorMessage.value = message
//        loading.value = false
  }

  override fun onCleared() {
    super.onCleared()
    job?.cancel()
  }


}