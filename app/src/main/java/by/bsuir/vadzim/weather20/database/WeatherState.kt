package by.bsuir.vadzim.weather20.database

data class WeatherState(
    val weatherInfo: List<WeatherInfo> = emptyList(),
    val weatherType: WeatherType = WeatherType.Cloudy,
    val isAddingInfo: Boolean = false,
    val isFavorite: Boolean = false
)