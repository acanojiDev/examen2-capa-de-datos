package com.turingalan.examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.turingalan.examen.ui.navigation.NavGraph

import com.turingalan.examen.ui.theme.ExamenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenTheme {
                NavGraph()
            }
        }
    }
}

