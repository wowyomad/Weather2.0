package by.bsuir.vadzim.weather20.database

sealed interface WeatherEvent {
    object SaveWeather: WeatherEvent

    data class SetType(val type: WeatherType): WeatherEvent

    object MakeFavorite: WeatherEvent

    object Favorite: WeatherEvent

    object Unfavorite: WeatherEvent

    object ShowDialog: WeatherEvent

    object HideDialog: WeatherEvent

    data class DeleteWeather(val weather: WeatherInfo): WeatherEvent


}