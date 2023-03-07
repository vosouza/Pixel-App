package com.evosouza.pixelapp.framework.di

//import com.evosouza.pixelapp.framework.network.interceptor.AuthorizationInterceptor
import com.evosouza.pixelapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

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

//    @Provides
//    fun provideAuthorizationInterceptor() = AuthorizationInterceptor(BuildConfig.PRIVATE_KEY)

//    @Provides
//    fun provideOkHttpClient(
//        loggingInterceptor: HttpLoggingInterceptor,
//        authorizationInterceptor: AuthorizationInterceptor
//    ): OkHttpClient = OkHttpClient.Builder()
//        .readTimeout()

}