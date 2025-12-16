package com.turingalan.examen.ui.results

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.turingalan.examen.data.model.Book
import com.turingalan.examen.data.repository.BookRepository
import com.turingalan.examen.ui.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookResultsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val repository: BookRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<ResultUiState> = MutableStateFlow(ResultUiState.Initial)
    val uiState: StateFlow<ResultUiState>
        get() = _uiState.asStateFlow()

    init {
        val route = savedStateHandle.toRoute<Routes.Result>()

        viewModelScope.launch {
            //
            _uiState.value = ResultUiState.Loading
            repository.observeByQuery(route.search).collect {
                result ->
                    if (result.isSuccess) {
                        val books = result.getOrNull()!!
                        if (books.isEmpty()) {
                            _uiState.value = ResultUiState.Error("No books found")
                        }
                        else {
                            _uiState.value = ResultUiState.Success(books.toResultItemUiState())
                        }
                    }
                else{
                        _uiState.value = ResultUiState.Error("Unknown error")
            }


            }
        }
    }
}

fun Book.toResultItemUiState(): ResultItemUiState {
    return ResultItemUiState(
        id = this.id,
        title = this.title,
    )
}

fun List<Book>.toResultItemUiState():List<ResultItemUiState> {
    return this.map( Book::toResultItemUiState)
}

data class ResultItemUiState(
    val id:String,
    val title:String
)
sealed class ResultUiState {

    object Initial: ResultUiState()
    object Loading: ResultUiState()
    data class Success(val books:List<ResultItemUiState>): ResultUiState()
    data class Error(val message:String): ResultUiState()
}