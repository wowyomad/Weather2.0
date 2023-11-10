package by.bsuir.vadzim.weather20.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import by.bsuir.vadzim.weather20.R

@Database(entities = [WeatherInfo::class], version = 3)
@TypeConverters(WeatherTypeConverter::class)
abstract class WeatherInfoDatabase :  RoomDatabase(){
    abstract val dao: WeatherInfoDao
}