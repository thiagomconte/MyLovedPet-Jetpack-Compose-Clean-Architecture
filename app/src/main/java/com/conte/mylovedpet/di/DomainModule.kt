package com.conte.mylovedpet.di

import com.conte.domain.module.pet.repository.PetRepository
import com.conte.domain.module.pet.usecase.GetAllPetsUseCase
import com.conte.domain.module.pet.usecase.InsertPetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideGetAllPetsUseCase(repository: PetRepository): GetAllPetsUseCase {
        return GetAllPetsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideInsertPetUseCase(repository: PetRepository): InsertPetUseCase {
        return InsertPetUseCase(repository)
    }
}