package com.conte.mylovedpet.di

import android.content.Context
import androidx.room.Room
import com.conte.data.module.database.AppDatabase
import com.conte.data.module.database.dao.PetDao
import com.conte.data.module.database.dao.PetEventDao
import com.conte.data.module.pet.repository.PetLocalDataSource
import com.conte.data.module.pet.repository.PetRepositoryImpl
import com.conte.data.module.petevent.repository.PetEventLocalDataSource
import com.conte.data.module.petevent.repository.PetEventRepositoryImpl
import com.conte.domain.module.event.repository.PetEventRepository
import com.conte.domain.module.pet.repository.PetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "my_loved_pet"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providePetDao(db: AppDatabase): PetDao {
        return db.petDao()
    }

    @Singleton
    @Provides
    fun provideEventDao(db: AppDatabase): PetEventDao {
        return db.eventDao()
    }

    @Singleton
    @Provides
    fun providePetLocalDataSource(dao: PetDao): PetLocalDataSource {
        return PetLocalDataSource(dao)
    }

    @Singleton
    @Provides
    fun providePetEventLocalDataSource(dao: PetEventDao): PetEventLocalDataSource {
        return PetEventLocalDataSource(dao)
    }

    @Singleton
    @Provides
    fun providePetRepository(localDataSource: PetLocalDataSource): PetRepository {
        return PetRepositoryImpl(localDataSource)
    }

    @Singleton
    @Provides
    fun providePetEventRepository(localDataSource: PetEventLocalDataSource): PetEventRepository {
        return PetEventRepositoryImpl(localDataSource)
    }
}