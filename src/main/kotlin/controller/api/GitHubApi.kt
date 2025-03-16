package controller.api

import model.Repository
import model.User

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<User>

    @GET("users/{username}/repos")
    fun getUserRepositories(@Path("username") username: String): Call<List<Repository>>
}