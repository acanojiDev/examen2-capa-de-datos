package com.turingalan.examen.ui.search

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class BookSearchViewModel @Inject constructor(): ViewModel()
{

    val searchFieldState = TextFieldState()


    val canSearch:Boolean
        get() {
            val search =  searchFieldState.text.toString()
            return search.isNotBlank() && search.length >= 3
    }
}
