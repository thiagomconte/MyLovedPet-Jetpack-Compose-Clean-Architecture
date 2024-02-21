package com.conte.mylovedpet.di

import com.conte.domain.module.pet.repository.PetRepository
import com.conte.domain.module.pet.usecase.FlowAllPetsUseCase
import com.conte.domain.module.pet.usecase.GetAllPetsUseCase
import com.conte.domain.module.pet.usecase.GetPetByIdUseCase
import com.conte.domain.module.pet.usecase.InsertPetUseCase
import com.conte.domain.module.petevent.repository.PetEventRepository
import com.conte.domain.module.petevent.usecase.FlowAllPetEventsByPetUseCase
import com.conte.domain.module.petevent.usecase.GetAllPetEventsByPetUseCase
import com.conte.domain.module.petevent.usecase.InsertPetEventUseCase
import com.conte.domain.module.petevent.usecase.UpdatePetEventNotificationIdUseCase
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

    @Singleton
    @Provides
    fun provideFlowAllPetsUseCase(repository: PetRepository): FlowAllPetsUseCase {
        return FlowAllPetsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetPetByIdUseCase(repository: PetRepository): GetPetByIdUseCase {
        return GetPetByIdUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetAllPetEventsByPetUseCase(repository: PetEventRepository): GetAllPetEventsByPetUseCase {
        return GetAllPetEventsByPetUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideInsertPetEventUseCase(repository: PetEventRepository): InsertPetEventUseCase {
        return InsertPetEventUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideUpdatePetEventNotificationIdUseCase(repository: PetEventRepository): UpdatePetEventNotificationIdUseCase {
        return UpdatePetEventNotificationIdUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideFlowAllPetEventsByPetUseCase(repository: PetEventRepository): FlowAllPetEventsByPetUseCase {
        return FlowAllPetEventsByPetUseCase(repository)
    }
}