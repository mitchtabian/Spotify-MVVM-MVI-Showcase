package com.sean.spotifyapp.di.module

import android.app.Application
import androidx.fragment.app.FragmentFactory
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sean.myapplication.di.MainScope
import com.sean.spotifyapp.fragment.MainFragmentFactory
import com.sean.spotifyapp.network.ApiService
import com.sean.spotifyapp.persistence.MainDao
import com.sean.spotifyapp.persistence.AppDatabase
import com.sean.spotifyapp.persistence.AppDatabase.Companion.DATABASE_NAME
import com.sean.spotifyapp.persistence.AuthDao
import com.sean.spotifyapp.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@InternalCoroutinesApi
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(5, TimeUnit.SECONDS)
        httpClient.readTimeout(5, TimeUnit.SECONDS)
        httpClient.writeTimeout(5, TimeUnit.SECONDS)

        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl("https://api.spotify.com/v1/").client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()

        return retrofit
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDatabase = Room
        .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideAuthDao(db: AppDatabase): AuthDao = db.getAuthDao()


}