package com.scotia.githubusers.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scotia.githubusers.R
import com.scotia.githubusers.models.ReposUiState

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