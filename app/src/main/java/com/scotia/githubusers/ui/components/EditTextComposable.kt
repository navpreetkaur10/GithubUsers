package com.scotia.githubusers.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.scotia.githubusers.ui.MainViewModel

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