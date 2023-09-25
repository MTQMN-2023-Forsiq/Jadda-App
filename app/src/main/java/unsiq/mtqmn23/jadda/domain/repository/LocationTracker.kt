package unsiq.mtqmn23.jadda.domain.repository

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}