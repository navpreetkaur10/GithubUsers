package com.scotia.githubusers.network

import com.scotia.githubusers.models.ReposUiState
import com.scotia.githubusers.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): User

    @GET("users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String): List<ReposUiState>
}