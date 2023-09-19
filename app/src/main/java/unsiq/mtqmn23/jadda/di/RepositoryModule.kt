package unsiq.mtqmn23.jadda.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import unsiq.mtqmn23.jadda.data.repository.AuthRepositoryImpl
import unsiq.mtqmn23.jadda.data.repository.TajweedRepositoryImpl
import unsiq.mtqmn23.jadda.domain.repository.AuthRepository
import unsiq.mtqmn23.jadda.domain.repository.TajweedRepository
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
}