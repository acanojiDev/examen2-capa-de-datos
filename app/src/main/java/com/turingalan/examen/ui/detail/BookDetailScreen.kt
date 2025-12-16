package com.turingalan.examen.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turingalan.examen.R


@Composable
fun BookDetailScreen(
    modifier:Modifier = Modifier,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BookDetailSceen(
        modifier = modifier,
        title = uiState.title,
        publicationYear = uiState.publicationYear,
        authors = uiState.authors
    )
}

@Composable
fun BookDetailSceen(
    modifier: Modifier = Modifier,
    title:String,
    publicationYear: String,
    authors: List<String>,

    )
{
        Column(
            modifier = modifier.fillMaxSize().padding(8.dp)
                .background(MaterialTheme.colorScheme.surfaceContainer),
            verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {
            Text(title,
                style = MaterialTheme.typography.headlineMedium,
             )
            Text(
                text = "Published in $publicationYear",
                style = MaterialTheme.typography.bodyMedium
            )
            if (authors.isNotEmpty()) {
                Text(
                    text=pluralStringResource(R.plurals.authors,authors.size),
                    style = MaterialTheme.typography.titleMedium
                )
                for (author in authors) {
                    Text(
                        text = author,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }


}

