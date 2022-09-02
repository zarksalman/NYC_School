package com.nyc.highschool.feature.nychighSchool.list_api_interface

interface IPopularNycHighSchool {
  fun getNycHighSchool(query: String, apiToken: String)
}