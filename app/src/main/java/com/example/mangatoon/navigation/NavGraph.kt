package com.example.mangatoon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mangatoon.screens.DetailScreen
import com.example.mangatoon.screens.FavoritesScreen
import com.example.mangatoon.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeScreen(onItemClick = { webtoon ->
                navController.navigate("Detail/${webtoon.title}")
            }, navigateToFavorites = {
                navController.navigate("Favorites")
            })
        }
        composable("Detail/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            if (title != null) {
                DetailScreen(webtoonTitle = title)
            }
        }
        composable("Favorites"){
            FavoritesScreen()
        }
    }
}