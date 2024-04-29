package com.scotia.githubusers.models


import com.google.gson.annotations.SerializedName

data class UserUiState(
    @SerializedName("login")
    val username: String? = "",

    @SerializedName("name")
    val fullName: String? = "",

    @SerializedName("avatar_url")
    var avatarUrl: String? = "",
)




