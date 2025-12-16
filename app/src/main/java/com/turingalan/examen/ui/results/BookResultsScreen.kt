package com.turingalan.examen.ui.results

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun BookResultsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookResultsViewModel = hiltViewModel(),
    toDetail: (String)->Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(uiState) {
        ResultUiState.Initial -> {}
        is ResultUiState.Error -> {
            ResultErrorScreen(modifier = modifier,
                message = (uiState as ResultUiState.Error).message)

        }
        ResultUiState.Loading -> {
            ResultLoadingScreen(modifier)
        }
        is ResultUiState.Success -> {
            ResultSuccessScreen(
                modifier = modifier,
                books = (uiState as ResultUiState.Success).books,
                toDetail = toDetail)
        }
    }

}

@Composable
fun ResultSuccessScreen(
    modifier: Modifier = Modifier,
    books:List<ResultItemUiState>,
    toDetail: (String) -> Unit,
) {
    LazyColumn(
        modifier= modifier.background(MaterialTheme.colorScheme.surfaceContainer).fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)

        ) {
        item {
            ResultSuccessCountCard(
                modifier = Modifier.fillMaxWidth(),
                bookCount = books.size)
        }
        items(items = books) {
            book ->
            ResultItemCard(
                modifier = Modifier.fillMaxWidth(),
                book= book,
                toDetail = toDetail,
                )

        }
    }

}
@Composable
fun ResultItemCard(
    modifier:Modifier = Modifier,
    book: ResultItemUiState,
    toDetail: (String) -> Unit)
{
    Card(
        modifier = modifier.clickable(onClick = {toDetail(book.id)}).height(64.dp),
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = book.title, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun ResultSuccessCountCard(
    modifier:Modifier = Modifier,
    bookCount:Int) {
    val cardColor = CardDefaults.cardColors(
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
    )
    Card(
        modifier = modifier.height(64.dp),
        colors = cardColor,

    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Hay $bookCount libros encontrados", modifier = Modifier.padding(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ResultLoadingScreen(
    modifier:Modifier = Modifier
)
{
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoadingIndicator(modifier = modifier)
    }
}

@Composable
fun ResultErrorScreen(
    modifier:Modifier = Modifier,
    message:String,
) {


    Column(
        modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.errorContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
            Text( modifier = Modifier.padding(8.dp),
                text = message,
                color = MaterialTheme.colorScheme.onError)

    }


}