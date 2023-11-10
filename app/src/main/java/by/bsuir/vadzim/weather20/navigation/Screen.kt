package by.bsuir.vadzim.weather20.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import by.bsuir.vadzim.weather20.R


const val ROOT_GRAPH_ROUTE = "root"
const val HOME_GRAPH_ROUTE = "home"
const val FAVORITES_GRAPH_ROUTE = "favorites"
const val SETTINGS_GRAPH_ROUTE = "settings"

sealed class Screen(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector,
    val isSelected: Boolean = false,
    val iconSelected: ImageVector
) {
    object Home : Screen(
        route = "home_screen",
        title = R.string.home_title,
        icon = Icons.Outlined.Home,
        iconSelected = Icons.Filled.Home
    )

    object Favorites : Screen(
        route = "favorites_screen",
        title = R.string.favorites_title,
        icon = Icons.Outlined.FavoriteBorder,
        iconSelected = Icons.Filled.Favorite
    )

    object Settings : Screen(
        route = "settings_screen",
        title = R.string.settings_title,
        icon = Icons.Outlined.Settings,
        iconSelected = Icons.Filled.Settings
    )
    {
        object About : Screen (
            route = "settings_about_screen",
            title = R.string.settings_about_title,
            icon = Icons.Outlined.Info,
            iconSelected = Icons.Filled.Info
        )
    }
}