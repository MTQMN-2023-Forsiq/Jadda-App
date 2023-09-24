package unsiq.mtqmn23.jadda.domain.repository

import android.location.Location
import kotlinx.coroutines.flow.Flow
import unsiq.mtqmn23.jadda.domain.model.compass.LocationStatus

interface CompassRepository {
    fun startObservingRotation()
    fun stopObservingRotation()
    fun getRotation(): Flow<Int>
    fun startLocationUpdates()
    fun stopLocationUpdates()
    fun getUserLocation(): Flow<LocationStatus>
    fun getDestinationLocation(): Flow<Location>
}