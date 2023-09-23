package unsiq.mtqmn23.jadda.presentation.screen.home

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.LocationTracker
import unsiq.mtqmn23.jadda.domain.repository.SalatRepository
import unsiq.mtqmn23.jadda.domain.repository.TajweedRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tajweedRepository: TajweedRepository,
    private val locationTracker: LocationTracker,
    private val salatRepository: SalatRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getTajweed()
        getCurrentLocation()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Refresh -> {
                getTajweed()
            }
            is HomeEvent.OnGetCurrentLocation -> {
                getCurrentLocation()
            }
            is HomeEvent.OnGetSalatSchedule -> {
                getSalatSchedule(event.city)
            }
            is HomeEvent.OnTajweedCardClick -> {
                _state.update {
                    val currentIds = it.expandableCardIds
                    it.copy(
                        expandableCardIds = currentIds.also { list ->
                            if (list.contains(event.id)) list.remove(event.id) else list.add(event.id)
                        }
                    )
                }
            }
        }
    }

    private fun getTajweed() = viewModelScope.launch {
        tajweedRepository.getAllTajweed().collect { result ->
            when (result) {
                is Result.Error -> _state.update { it.copy(isError = true) }
                is Result.Loading -> _state.update { it.copy(isLoading = true) }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            listTajweed = result.data.toMutableStateList(),
                            statusMessage = result.data.toString()
                        )
                    }
                }
            }
        }
    }

    private fun getCurrentLocation() = viewModelScope.launch {
        val currentLocation = locationTracker.getCurrentLocation()
        _state.update {
            it.copy(
                currentLocation = currentLocation
            )
        }
    }

    private fun getSalatSchedule(city: String) = viewModelScope.launch {
        _state.update {
            it.copy(
                city = city
            )
        }
        salatRepository.getSalatSchedule(city).collect { result ->
            when (result) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            salatDate = result.data
                        )
                    }
                }
            }
        }
    }
}