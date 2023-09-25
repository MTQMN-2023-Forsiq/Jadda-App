package unsiq.mtqmn23.jadda.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import unsiq.mtqmn23.jadda.data.repository.LocationTrackerImpl
import unsiq.mtqmn23.jadda.domain.repository.LocationTracker
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFusedLocationProviderClient(
        application: Application
    ): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    @Provides
    @Singleton
    fun providesLocationTracker(
        fusedLocationProviderClient: FusedLocationProviderClient,
        application: Application
    ): LocationTracker = LocationTrackerImpl(
        fusedLocationProviderClient = fusedLocationProviderClient,
        application = application
    )
}