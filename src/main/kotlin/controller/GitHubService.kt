package controller

import controller.api.GitHubApiService
import model.User


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubService {
    private val apiService: GitHubApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(GitHubApiService::class.java)
    }

    fun fetchUser(username: String): User? {
        val userCall = apiService.getUser(username)
        val userResponse = userCall.execute()
        if (!userResponse.isSuccessful) return null

        val user = userResponse.body()!!
        val repoCall = apiService.getUserRepositories(username)
        val repoResponse = repoCall.execute()
        if (!repoResponse.isSuccessful) return null

        val repositories = repoResponse.body()!!
        return user.copy(repositories = repositories)
    }
}