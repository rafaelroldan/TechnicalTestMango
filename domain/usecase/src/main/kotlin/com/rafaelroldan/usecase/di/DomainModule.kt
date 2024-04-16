package com.rafaelroldan.usecase.di

import com.rafaelroldan.usecase.character.GetCharacterUseCase
import com.rafaelroldan.usecase.character.GetCharacterUseCaseImpl
import com.rafaelroldan.usecase.comic.GetComicUseCase
import com.rafaelroldan.usecase.comic.GetComicUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindGetCharacterUseCase(getCharacterUseCaseImpl: GetCharacterUseCaseImpl): GetCharacterUseCase

    @Binds
    fun bindGetComicUseCase(getComicUseCaseImpl: GetComicUseCaseImpl): GetComicUseCase
}
