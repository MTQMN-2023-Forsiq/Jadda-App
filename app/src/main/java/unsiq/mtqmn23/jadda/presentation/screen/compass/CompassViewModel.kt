package unsiq.mtqmn23.jadda.presentation.screen.compass


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.model.compass.DestinationStatus
import unsiq.mtqmn23.jadda.domain.model.compass.LocationStatus
import unsiq.mtqmn23.jadda.domain.repository.CompassRepository
import unsiq.mtqmn23.jadda.util.toRotationInDegrees
import javax.inject.Inject

@HiltViewModel
class CompassViewModel @Inject constructor(
    private val compassRepository: CompassRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CompassState())
    val state = _state.asStateFlow()

    init {
        getRotation()
        getCompassLocation()
    }

    fun onEvent(event: CompassEvent) {
        when (event) {
            is CompassEvent.OnStartStopObserving -> {
                if (event.observing) {
                    compassRepository.startObservingRotation()
                    compassRepository.startLocationUpdates()
                }
                else {
                    compassRepository.stopObservingRotation()
                    compassRepository.stopLocationUpdates()
                }
            }
        }
    }

    private fun getRotation() = viewModelScope.launch {
        compassRepository.getRotation().collect { rotation ->
            _state.update {
                it.copy(
                    rotation = rotation
                )
            }
        }
    }

    private fun getCompassLocation() = viewModelScope.launch {
        val destinationStatus = compassRepository.getUserLocation().combine(compassRepository.getDestinationLocation()) { loc, destination ->
            if (loc is LocationStatus.Success) {
                _state.update {
                    it.copy(
                        addressCity = loc.addressCity
                    )
                }
                DestinationStatus(
                    distance = loc.location.distanceTo(destination),
                    direction = loc.location.bearingTo(destination).toRotationInDegrees()
                )
            } else {
                null
            }
        }
        destinationStatus.collect { destination ->
            _state.update {
                it.copy(
                    destinationStatus = destination
                )
            }
        }
    }
}