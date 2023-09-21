package unsiq.mtqmn23.jadda.presentation.screen.tajweeddetect

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
class TajweedDetectViewModel @Inject constructor(
    private val tajweedRepository: TajweedRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(TajweedDetectUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: TajweedDetectEvent) {
        when (event) {
            is TajweedDetectEvent.OnBoundsClick -> {
                getDetailTajweed(event.tajweedId)
                _state.update {
                    it.copy(
                        isSheetOpen = true
                    )
                }
            }
            is TajweedDetectEvent.OnDismissSheet -> {
                _state.update {
                    it.copy(
                        isSheetOpen = false
                    )
                }
            }
        }
    }

    private fun getDetailTajweed(id: String) = viewModelScope.launch {
        tajweedRepository.getTajweedById(id).collect { result ->
            when (result) {
                is Result.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        statusMessage = result.message
                    )
                }
                is Result.Loading -> _state.update {
                    it.copy(
                        isLoading = true,
                        isError = false,
                        statusMessage = null
                    )
                }
                is Result.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        statusMessage = null,
                        detailTajweed = result.data
                    )
                }
            }
        }
    }
}