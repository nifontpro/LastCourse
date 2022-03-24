package ru.nifontbus.les1_data.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nifontbus.les1_data.db.SpotDatabase
import ru.nifontbus.les1_data.repository.SpotRepositoryImpl
import ru.nifontbus.les1_domain.repository.SpotRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapDataModule {
    @Singleton
    @Provides
    fun provideSpotDatabase(app: Application): SpotDatabase {
        return Room.databaseBuilder(
            app,
            SpotDatabase::class.java,
            "maps_spots.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideSpotRepository(db: SpotDatabase): SpotRepository {
        return SpotRepositoryImpl(db.dao)
    }
}