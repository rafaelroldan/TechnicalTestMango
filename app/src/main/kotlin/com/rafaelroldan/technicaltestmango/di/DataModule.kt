package com.rafaelroldan.technicaltestmango.di

import com.google.gson.GsonBuilder
import com.rafaelroldan.common.Utils.DATE_FORMAT
import com.rafaelroldan.network.service.net.CharacterMarvelApi
import com.rafaelroldan.network.service.net.ComicMarvelApi
import com.rafaelroldan.network.service.net.QueryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @ApiEndpoint
    fun provideApiEndpoint(): String = "https://gateway.marvel.com/"

    @Provides
    fun provideOkHttpClient(requestInterceptor: QueryInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()

    @Provides
    fun provideRetrofitClient(
        @ApiEndpoint apiEndPoint: String,
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(apiEndPoint)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setDateFormat(DATE_FORMAT).create(),
                ),
            )
            .client(okHttpClient)
            .build()

    @Provides
    fun provideCharacterMarvelApi(retrofit: Retrofit): CharacterMarvelApi = retrofit.create()

    @Provides
    fun provideComicMarvelApi(retrofit: Retrofit): ComicMarvelApi = retrofit.create()
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndpoint
