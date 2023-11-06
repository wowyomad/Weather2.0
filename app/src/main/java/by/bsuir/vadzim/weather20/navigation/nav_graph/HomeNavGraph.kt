package by.bsuir.vadzim.weather20.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.database.WeatherState
import by.bsuir.vadzim.weather20.navigation.HOME_GRAPH_ROUTE
import by.bsuir.vadzim.weather20.navigation.Screen
import by.bsuir.vadzim.weather20.screens.HomeScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    state: WeatherState,
    onEvent: (WeatherEvent) -> Unit
) {
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(state = state, onEvent = onEvent)
        }
    }
}