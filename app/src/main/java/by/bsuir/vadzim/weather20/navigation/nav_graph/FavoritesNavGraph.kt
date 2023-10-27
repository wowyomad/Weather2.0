package by.bsuir.vadzim.weather20.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import by.bsuir.vadzim.weather20.navigation.FAVORITES_GRAPH_ROUTE
import by.bsuir.vadzim.weather20.navigation.Screen

fun NavGraphBuilder.favoritesNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Favorites.route,
        route = FAVORITES_GRAPH_ROUTE
    ) {
        composable(route = Screen.Favorites.route) {

        }
    }
}