package com.evosouza.pixelapp.framework.di

import com.evosouza.pixelapp.BuildConfig
import com.evosouza.pixelapp.framework.network.WallpaperAPI
import com.evosouza.pixelapp.framework.network.interceptor.AuthorizationInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val READ_TIME_OUT = 15L

    @Provides
    fun provideLogInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(
            if(BuildConfig.DEBUG){
                HttpLoggingInterceptor.Level.BODY
            }else{
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }

    @Provides
    fun provideAuthorizationInterceptor() = AuthorizationInterceptor(BuildConfig.PRIVATE_KEY)

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authorizationInterceptor)
        .build()
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    fun provideWallPaperAPI(httpClient: OkHttpClient, factory: GsonConverterFactory): WallpaperAPI = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient)
        .addConverterFactory(factory)
        .build()
        .create(WallpaperAPI::class.java)
}