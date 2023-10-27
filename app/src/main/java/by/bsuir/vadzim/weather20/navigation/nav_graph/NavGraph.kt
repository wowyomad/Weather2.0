package by.bsuir.vadzim.weather20.navigation.nav_graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import by.bsuir.vadzim.weather20.navigation.HOME_GRAPH_ROUTE
import by.bsuir.vadzim.weather20.navigation.ROOT_GRAPH_ROUTE


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = HOME_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
    ) {
        homeNavGraph(navController)
        favoritesNavGraph(navController)
        settingsNavGraph(navController)
    }
}