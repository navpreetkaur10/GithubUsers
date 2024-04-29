package com.scotia.githubusers.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.scotia.githubusers.R
import com.scotia.githubusers.models.User

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Avatar(user: User) {
    if (user.avatarUrl?.isEmpty()?.not() == true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                model = user.avatarUrl,
                contentDescription = stringResource(R.string.avatar_url),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            if (user.fullName != null)
                Text(text = user.fullName)
        }
    }
}