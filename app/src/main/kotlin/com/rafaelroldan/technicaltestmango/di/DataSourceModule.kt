package com.rafaelroldan.technicaltestmango.di

import com.rafaelroldan.network.remote.character.CharacterRemoteDataSource
import com.rafaelroldan.network.remote.character.CharacterRemoteDataSourceImpl
import com.rafaelroldan.network.remote.comic.ComicRemoteDataSource
import com.rafaelroldan.network.remote.comic.ComicRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindsCharacterRemoteDatasource(characterRemoteDataSourceImpl: CharacterRemoteDataSourceImpl): CharacterRemoteDataSource

    @Binds
    fun bindsComicRemoteDatasource(comicRemoteDataSourceImpl: ComicRemoteDataSourceImpl): ComicRemoteDataSource
}
