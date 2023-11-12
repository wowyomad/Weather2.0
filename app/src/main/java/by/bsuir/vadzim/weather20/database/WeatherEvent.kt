package by.bsuir.vadzim.weather20.database

import android.content.Context

sealed interface WeatherEvent {
    data class SaveWeather(val weather: Weather?, val context: Context?): WeatherEvent

    data class SetType(val weatherType: WeatherType): WeatherEvent

    object MakeFavorite: WeatherEvent

    data class Favorite(val weather: Weather): WeatherEvent

    object Unfavorite: WeatherEvent

    object ShowDialog: WeatherEvent

    data class ShowEditDialog(val weather: Weather) : WeatherEvent

    data class ShowRemoveDialog(val weather: Weather): WeatherEvent

    object HideDialog: WeatherEvent

    data class NukeTable(val context: Context? = null): WeatherEvent

    object RefreshData: WeatherEvent

    data class SetWeather (val weather: Weather) : WeatherEvent
    data class SetDescription (val description: String) : WeatherEvent

    object ClearWeather : WeatherEvent

    data class SetGroup(val weatherGroup: WeatherGroup): WeatherEvent

    data class SetContext(val context: Context): WeatherEvent

    data class DeleteWeather(val weather: Weather?, val context: Context? = null): WeatherEvent
    object HideRemoveDialog : WeatherEvent


}