package com.nyc.highschool.base

import com.nyc.highschool.feature.satscore.satscoreresponse.SatScoreResponse
import com.nyc.highschool.feature.nychighSchool.listResponse.AllListResponse
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiHelper {

  suspend fun getNycHighSchoolList(
    @Path("filter") filter: String,
    @Query("api_key") apiKey: String
  ): Response<AllListResponse>


  suspend fun getSatScore(
    @Path("movie_id") filter: Int,
    @Query("api_key") apiKey: String
  ): Response<SatScoreResponse>
}