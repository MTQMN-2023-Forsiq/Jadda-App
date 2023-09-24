package unsiq.mtqmn23.jadda.domain.model.compass

import android.location.Location

sealed class LocationStatus {
    object Loading : LocationStatus()
    data class Success(val location: Location, val addressCity: String) : LocationStatus()
}