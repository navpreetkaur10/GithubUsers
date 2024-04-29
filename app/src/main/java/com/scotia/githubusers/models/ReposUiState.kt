package com.scotia.githubusers.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReposUiState(
    val name: String,
    val description: String?,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    val forks: Int
) : Parcelable