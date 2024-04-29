package com.scotia.githubusers

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.scotia.githubusers.models.ReposUiState
import com.scotia.githubusers.models.UserUiState
import com.scotia.githubusers.ui.theme.GithubUsersTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        UserIdEditText(viewModel)
                        Avatar(viewModel.uiState.collectAsState().value)
                        Repositories(repos = viewModel.reposState.collectAsState().value) { repo ->
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserIdEditText(viewModel: MainViewModel) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            modifier = Modifier.weight(1f),
            label = { Text("Enter a github user id") }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = {
                keyboardController?.hide()
                viewModel.getUserInformation(text)
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text("Search")
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Avatar(userUiState: UserUiState) {
    if (userUiState.avatarUrl?.isEmpty()?.not() == true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                model = userUiState.avatarUrl,
                contentDescription = stringResource(R.string.avatar_url),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            if (userUiState.fullName != null)
                Text(text = userUiState.fullName)
        }
    }
}

@Composable
fun Repositories(repos: List<ReposUiState>, onClick: (ReposUiState) -> Unit) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(10.dp)) {
        items(repos.count()) {
            Repository(repo = repos[it], onClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Repository(repo: ReposUiState, onClick: (ReposUiState) -> Unit) {
    OutlinedCard(
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        border = BorderStroke(1.dp, Color.Gray),
        onClick = {
            onClick(repo)
        }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = repo.name,
                    modifier = Modifier.padding(10.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                if (repo.description.isNullOrEmpty().not()) {
                    Text(
                        text = repo.description ?: "",
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .padding(horizontal = 10.dp)
                    )
                }
            }
            if (repo.forks > 5000) {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Image",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun UserIdZEditTextPreview() {
    GithubUsersTheme {
        UserIdEditText(MainViewModel())
        Avatar(UserUiState())
    }
}