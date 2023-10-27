package by.bsuir.vadzim.weather20.database

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import by.bsuir.vadzim.weather20.R
import java.util.Date

@Entity
data class WeatherInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: WeatherType,
    val isFavorite: Boolean = false
)

sealed class WeatherType (
    @StringRes val name: Int,
    @DrawableRes val icon: Int
) {
    object Cloudy : WeatherType(
        name = R.string.weather_cloudy_name,
        icon = R.drawable.cloudy
    )

    object Sunny : WeatherType(
        name = R.string.weather_sunny_name,
        icon = R.drawable.sunny
    )

    object Rainy : WeatherType(
        name = R.string.weather_rainy_name,
        icon = R.drawable.rainy
    )
}

class WeatherTypeConverter {
    @TypeConverter
    fun fromWeatherType(weatherType: WeatherType): String {
        return weatherType.javaClass.simpleName
    }
    @TypeConverter
    fun toWeatherType(weatherTypeString: String): WeatherType {
        return when (weatherTypeString) {
            "Cloudy" -> WeatherType.Cloudy
            "Sunny" -> WeatherType.Sunny
            "Rainy" -> WeatherType.Rainy
            else -> throw IllegalArgumentException("Unsupported WeatherType: $weatherTypeString")
        }
    }
}
