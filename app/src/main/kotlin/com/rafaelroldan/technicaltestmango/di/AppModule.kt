package com.rafaelroldan.technicaltestmango.di

import com.rafaelroldan.network.service.net.PrivateKey
import com.rafaelroldan.network.service.net.PublicKey
import com.rafaelroldan.technicaltestmango.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @PrivateKey
    fun providePrivateKey(): String = BuildConfig.MARVEL_PRIVATE_KEY

    @Provides
    @PublicKey
    fun providePublicKey(): String = BuildConfig.MARVEL_PUBLIC_KEY
}