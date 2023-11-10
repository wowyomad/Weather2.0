package by.bsuir.vadzim.weather20.navigation.nav_graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import by.bsuir.vadzim.weather20.database.WeatherEvent
import by.bsuir.vadzim.weather20.navigation.SETTINGS_GRAPH_ROUTE
import by.bsuir.vadzim.weather20.navigation.Screen
import by.bsuir.vadzim.weather20.screens.SettingsAboutScreen
import by.bsuir.vadzim.weather20.screens.SettingsScreen

fun NavGraphBuilder.settingsNavGraph (
    navController: NavHostController,
    onEvent: (WeatherEvent) -> Unit
) {
    navigation(
        startDestination = Screen.Settings.route,
        route = SETTINGS_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.Settings.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            }
            ) {
            SettingsScreen(navController = navController, onEvent = onEvent)
        }
        composable(
            route = Screen.Settings.About.route
        ) {
            SettingsAboutScreen(navController = navController)
        }
    }
}