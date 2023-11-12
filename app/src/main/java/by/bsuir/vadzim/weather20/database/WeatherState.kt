package by.bsuir.vadzim.weather20.database

data class WeatherState(
    val weatherItems: List<Weather> = emptyList(),
    val currentWeather: Weather? = null,
    val weatherType: WeatherType = WeatherType.Cloudy,
    val weatherId: Int = 0,
    val weatherDescription: String = "",
    val isAddingInfo: Boolean = false,
    val isRemoving: Boolean = false,
    val weatherIsFavorite: Boolean = false,
    val isRefreshing: Boolean = false,
    val weatherGroup: WeatherGroup = WeatherGroup.ALL
)