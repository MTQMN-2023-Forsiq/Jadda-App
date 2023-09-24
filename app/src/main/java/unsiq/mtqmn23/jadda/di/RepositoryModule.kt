package unsiq.mtqmn23.jadda.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import unsiq.mtqmn23.jadda.data.repository.AuthRepositoryImpl
import unsiq.mtqmn23.jadda.data.repository.CompassRepositoryImpl
import unsiq.mtqmn23.jadda.data.repository.HadistRepositoryImpl
import unsiq.mtqmn23.jadda.data.repository.QuranRepositoryImpl
import unsiq.mtqmn23.jadda.data.repository.SalatRepositoryImpl
import unsiq.mtqmn23.jadda.data.repository.TafsirRepositoryImpl
import unsiq.mtqmn23.jadda.data.repository.TajweedRepositoryImpl
import unsiq.mtqmn23.jadda.data.repository.WatchRepositoryImpl
import unsiq.mtqmn23.jadda.domain.repository.AuthRepository
import unsiq.mtqmn23.jadda.domain.repository.CompassRepository
import unsiq.mtqmn23.jadda.domain.repository.HadistRepository
import unsiq.mtqmn23.jadda.domain.repository.QuranRepository
import unsiq.mtqmn23.jadda.domain.repository.SalatRepository
import unsiq.mtqmn23.jadda.domain.repository.TafsirRepository
import unsiq.mtqmn23.jadda.domain.repository.TajweedRepository
import unsiq.mtqmn23.jadda.domain.repository.WatchRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideTajweedRepository(tajweedRepositoryImpl: TajweedRepositoryImpl): TajweedRepository

    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun provideSalatRepository(salatRepositoryImpl: SalatRepositoryImpl): SalatRepository

    @Binds
    @Singleton
    abstract fun provideQuranRepository(quranRepositoryImpl: QuranRepositoryImpl): QuranRepository

    @Binds
    @Singleton
    abstract fun provideHadistRepository(hadistRepositoryImpl: HadistRepositoryImpl): HadistRepository

    @Binds
    @Singleton
    abstract fun provideWatchRepository(watchRepositoryImpl: WatchRepositoryImpl): WatchRepository

    @Binds
    @Singleton
    abstract fun provideTafsirRepository(tafsirRepositoryImpl: TafsirRepositoryImpl): TafsirRepository

    @Binds
    @Singleton
    abstract fun provideCompassRepository(compassRepositoryImpl: CompassRepositoryImpl): CompassRepository
}