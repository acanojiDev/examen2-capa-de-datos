package com.turingalan.examen.ui.detail

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
class BookDetailViewModel @Inject constructor (
    private val repository: BookRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState>
        get() = _uiState.asStateFlow()

    init {
        val route = savedStateHandle.toRoute<Routes.Detail>()
        viewModelScope.launch {
            val result = repository.readOne(route.id)
            if (result.isSuccess) {
                val book = result.getOrNull()!!
                _uiState.value = book.toDetailUiState()
            }

        }
    }
}

data class DetailUiState(
    val id:String = "",
    val title:String = "",
    val publicationYear:String = "",
    val authors: List<String> = listOf(),
)

fun Book.toDetailUiState(): DetailUiState {
    return DetailUiState(
        id = this.id,
        title = this.title,
        publicationYear =  if (this.publicationYear > 0) publicationYear.toString() else "Unknown",
        authors = this.authors
    )
}