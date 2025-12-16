package com.turingalan.examen.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.turingalan.examen.R
import com.turingalan.examen.ui.theme.ExamenTheme


@Composable
fun BookSearchScreen(
    modifier: Modifier = Modifier,
    toSearch: (String)->Unit,
    viewModel: BookSearchViewModel = hiltViewModel()
) {

    BookSearchScreen(
        modifier = modifier,
        toSearch = toSearch,
        searchFieldState = viewModel.searchFieldState,
        canSearch = viewModel.canSearch
    )

}

@Composable
@Preview
fun BookSearchScreenPreview() {
    ExamenTheme {

    }
}
@Composable
fun BookSearchScreen(
    modifier: Modifier = Modifier,
    toSearch: (String) -> Unit,
    searchFieldState: TextFieldState,
    canSearch:Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    state = searchFieldState,
                    label = {
                        Text("Título")
                    },
                    placeholder = {
                        Text("The Two Towers")
                    },
                    lineLimits = TextFieldLineLimits.SingleLine

                )
                Button(
                    enabled = canSearch,
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    onClick = { toSearch(searchFieldState.text.toString()) }
                ) {
                    Text("Buscar")

                }


    }

}
