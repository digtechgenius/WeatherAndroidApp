package  com.weather

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weather.data.dto.FavCities
import com.weather.data.dto.WeatherEntity
import com.weather.data.local.FavCitiesDao
import com.weather.data.local.WeatherDao

// This is application wide RoomDatabase class
// Actual DB get created in AppModule
@Database(entities = [WeatherEntity::class, FavCities::class], version = 1, exportSchema = false)
abstract class WeatherAppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    abstract fun favCitiesDao(): FavCitiesDao
}