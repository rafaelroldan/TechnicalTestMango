package com.rafaelroldan.technicaltestmango.di

import com.rafaelroldan.repository.character.CharacterRepository
import com.rafaelroldan.repository.character.CharacterRepositoryImpl
import com.rafaelroldan.repository.comic.ComicRepository
import com.rafaelroldan.repository.comic.ComicRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    fun bindsComicRepository(comicRepositoryImpl: ComicRepositoryImpl): ComicRepository
}
