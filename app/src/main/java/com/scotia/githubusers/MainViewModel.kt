package com.scotia.githubusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scotia.githubusers.models.ReposUiState
import com.scotia.githubusers.models.UserUiState
import com.scotia.githubusers.network.GitHubService
import com.scotia.githubusers.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {

    // User UI state
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    // Repos UI state
    private val _reposState: MutableStateFlow<List<ReposUiState>> = MutableStateFlow(emptyList())
    val reposState: StateFlow<List<ReposUiState>> = _reposState.asStateFlow()


    fun getUserInformation(username: String) {
        val service = RetrofitClient.instance.create(GitHubService::class.java)

        viewModelScope.launch {
            try {
                val user = service.getUser(username)
                _uiState.value = user
                val repos = service.getRepos(username)
                _reposState.value = repos
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}