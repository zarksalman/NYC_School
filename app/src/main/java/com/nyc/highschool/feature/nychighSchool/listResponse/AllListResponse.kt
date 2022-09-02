package com.nyc.highschool.feature.nychighSchool.listResponse


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class AllListResponse(

  @SerializedName("page") val page: Int,
  @SerializedName("results") val results: ArrayList<BaseListResponse>,
  @SerializedName("total_pages") val total_pages: Int,
  @SerializedName("total_results") val total_results: Int,

  ) : Parcelable