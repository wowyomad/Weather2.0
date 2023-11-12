package by.bsuir.vadzim.weather20.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Weather::class], version = 4)
@TypeConverters(WeatherTypeConverter::class)
abstract class WeatherInfoDatabase :  RoomDatabase(){
    abstract val dao: WeatherInfoDao
}