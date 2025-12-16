package com.turingalan.examen.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.turingalan.examen.R
import com.turingalan.examen.ui.detail.BookDetailScreen
import com.turingalan.examen.ui.results.BookResultsScreen
import com.turingalan.examen.ui.search.BookSearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val  currentBackStackEntry  by  navController.currentBackStackEntryAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            MainNavigationBar(
                currentBackStackEntry,
                onBack = {navController.popBackStack()}
            )
        }
    ) {
        innerPadding ->
        val hostModifier = Modifier.consumeWindowInsets(innerPadding).padding(innerPadding)

        NavHost(
            navController = navController,
            startDestination = Routes.Search
        )
        {
            composable<Routes.Search> {
                BookSearchScreen(
                    modifier= hostModifier,
                    toSearch = {
                    search -> navController.navigate(Routes.Result(search))
                })
            }
            composable<Routes.Result> {
                BookResultsScreen(
                    modifier = hostModifier,
                    toDetail = {
                        id -> navController.navigate(Routes.Detail(id))
                    }
                )
            }
            composable<Routes.Detail> {
                BookDetailScreen(
                    modifier = hostModifier
                )
            }

        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun MainNavigationBar(
    currentNavStack: NavBackStackEntry?,
    onBack:()->Unit,
) {
    var showBack = true
    val route = currentNavStack?.destination?.route
    if (currentNavStack==null ||
        route?.contains("com.turingalan.examen.ui.navigation.Routes.Search",ignoreCase = true) == true
    ) {
        showBack = false
    }


    TopAppBar(
        modifier = Modifier.background(color= MaterialTheme.colorScheme.primaryContainer),
        title = {
            Text(
                "Turing Library",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        navigationIcon = {

            if (showBack) {
                IconButton(
                    onClick = onBack
                ) {
                    Icon(painterResource(R.drawable.arrow_back), "Back")
                }
            }
        },

    )
}

