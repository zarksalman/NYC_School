package com.nyc.highschool.base

import com.nyc.highschool.feature.satscore.satscoreresponse.SatScoreResponse
import com.nyc.highschool.feature.nychighSchool.listResponse.AllListResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
  private val apiService: ApiService
) : ApiHelper {
  override suspend fun getNycHighSchoolList(filter: String, apiKey: String): Response<AllListResponse> =
    apiService.getNycHighSchoolList(filter, apiKey)

  override suspend fun getSatScore(
    filter: Int,
    apiKey: String
  ): Response<SatScoreResponse> = apiService.getSatScore(filter, apiKey)

}