package com.conte.mylovedpet.di

import android.content.Context
import androidx.room.Room
import com.conte.data.module.database.AppDatabase
import com.conte.data.module.database.dao.PetDao
import com.conte.data.module.database.pet.repository.PetLocalDataSource
import com.conte.data.module.database.pet.repository.PetRepositoryImpl
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
    fun providePetLocalDataSource(dao: PetDao): PetLocalDataSource {
        return PetLocalDataSource(dao)
    }

    @Singleton
    @Provides
    fun providePetRepository(localDataSource: PetLocalDataSource): PetRepository {
        return PetRepositoryImpl(localDataSource)
    }
}