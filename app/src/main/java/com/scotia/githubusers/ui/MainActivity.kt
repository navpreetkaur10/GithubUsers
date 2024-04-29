package com.scotia.githubusers.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.scotia.githubusers.models.User
import com.scotia.githubusers.ui.components.Avatar
import com.scotia.githubusers.ui.components.Repositories
import com.scotia.githubusers.ui.components.UserIdEditText
import com.scotia.githubusers.ui.theme.GithubUsersTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        UserIdEditText(viewModel)

                        // Avatar will be changed based on the user
                        Avatar(viewModel.userState.collectAsState().value)

                        // Repositories will be shown in the recycler view
                        Repositories(repos = viewModel.reposState.collectAsState().value) { repo ->
                            // Clicking on the repo will move to the DetailsActivity
                            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                            intent.putExtra("bundle", Bundle().apply {
                                putParcelable("repo", repo)
                            })
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserIdZEditTextPreview() {
    GithubUsersTheme {
        UserIdEditText(MainViewModel())
        Avatar(User())
    }
}