package com.weather.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.weather.WeatherAppDatabase
import com.weather.utils.AppConstants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebase(@ApplicationContext context: Context) = FirebaseApp.initializeApp(context)

    @Provides
    @Singleton
    fun provideWeatherDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        WeatherAppDatabase::class.java,
        DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideWeatherDao(weatherDB: WeatherAppDatabase) = weatherDB.weatherDao()

    @Provides
    @Singleton
    fun provideFavDao(weatherDB: WeatherAppDatabase) = weatherDB.favCitiesDao()

    @Provides
    @Singleton
    fun provideFireBaseConfig() = FirebaseRemoteConfig.getInstance()

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}