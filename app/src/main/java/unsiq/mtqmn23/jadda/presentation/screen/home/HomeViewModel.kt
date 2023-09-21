package unsiq.mtqmn23.jadda.presentation.screen.home

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.TajweedRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tajweedRepository: TajweedRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getTajweed()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.Refresh -> {
                getTajweed()
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
}