package com.scotia.githubusers.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.scotia.githubusers.R
import com.scotia.githubusers.models.ReposUiState
import com.scotia.githubusers.ui.theme.GithubUsersTheme
import com.scotia.githubusers.utilities.parcelable

class DetailsActivity : ComponentActivity() {

    private var repo: ReposUiState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get Bundle and check for the parcelable "Repo" data.
        if (intent != null) {
            val bundle = intent.getBundleExtra("bundle")
            repo = bundle?.parcelable("repo")
        }
        setContent {
            GithubUsersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val details = repo
                    if (details != null) {
                        // If the forks are more than 5k, then change the color.
                        val textColor = if (details.forks > 5000) Color.Red else Color.Black
                        Column {
                            Text(
                                text = annotate("Name:", details.name),
                                modifier = Modifier.padding(10.dp)
                            )
                            if (details.description != null) {
                                Text(
                                    text = annotate("Description:", details.description ?: ""),
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                            Text(
                                text = annotate("Updated at:", details.updatedAt),
                                modifier = Modifier.padding(10.dp)
                            )
                            Row {
                                Text(
                                    text = annotate("Total Forks:", details.forks.toString()),
                                    modifier = Modifier.padding(10.dp),
                                    color = textColor
                                )
                                if (details.forks > 5000) {
                                    Image(
                                        painter = painterResource(id = R.drawable.star),
                                        contentDescription = "Image",
                                        modifier = Modifier.align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method will create a SpannableString. Bold Label with regular value.
     */
    private fun annotate(key: String, value: String) = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(key)
        }
        append(" ")
        append(value)
    }
}