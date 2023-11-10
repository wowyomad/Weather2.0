package by.bsuir.vadzim.weather20.database

import android.content.Context
import androidx.compose.runtime.Composable

sealed interface WeatherEvent {
    data class SaveWeather(val weather: WeatherInfo?, val context: Context?): WeatherEvent

    data class SetType(val weatherType: WeatherType): WeatherEvent

    object MakeFavorite: WeatherEvent

    data class Favorite(val weatherItem: WeatherInfo): WeatherEvent

    object Unfavorite: WeatherEvent

    object ShowDialog: WeatherEvent

    data class ShowEditDialog(val weather: WeatherInfo) : WeatherEvent


    object HideDialog: WeatherEvent

    object NukeTable: WeatherEvent

    object RefreshData: WeatherEvent

    data class SetWeather (val weather: WeatherInfo) : WeatherEvent
    data class SetDescription (val description: String) : WeatherEvent

    object ClearWeather : WeatherEvent

    data class SetGroup(val weatherGroup: WeatherGroup): WeatherEvent

    data class DeleteWeather(val weather: WeatherInfo): WeatherEvent


}