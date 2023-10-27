package by.bsuir.vadzim.weather20.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface WeatherInfoDao {
    @Upsert
    suspend fun insert(weatherInfo: WeatherInfo)
    @Delete
    suspend fun delete(weatherInfo: WeatherInfo)
    @Query("SELECT * FROM WeatherInfo")
    fun getAllWeatherInfo(): List<WeatherInfo>

    @Query("SELECT * FROM WeatherInfo WHERE isFavorite = 1")
    fun getFavoritesWeatherInfo(): List<WeatherInfo>
}