package by.bsuir.vadzim.weather20.database

sealed interface WeatherEvent {
    object SaveWeather: WeatherEvent

    data class SetType(val weatherType: WeatherType): WeatherEvent

    object MakeFavorite: WeatherEvent

    data class Favorite(val weatherItem: WeatherInfo): WeatherEvent

    object Unfavorite: WeatherEvent

    object ShowDialog: WeatherEvent

    object HideDialog: WeatherEvent

    object NukeTable: WeatherEvent

    data class DeleteWeather(val weather: WeatherInfo): WeatherEvent


}