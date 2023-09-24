package unsiq.mtqmn23.jadda.presentation.screen.tajweed

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
class TajweedViewModel @Inject constructor(
    private val tajweedRepository: TajweedRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(TajweedUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: TajweedEvent) {
        when (event) {
            is TajweedEvent.OnInitial -> {
                getTajweedById(event.id)
            }
        }
    }

    private fun getTajweedById(id: String) = viewModelScope.launch {
        tajweedRepository.getTajweedById(id).collect { result ->
            when (result) {
                is Result.Error -> _state.update {
                    it.copy(
                        statusMessage = result.message,
                    )
                }
                is Result.Loading -> _state.update {
                    it.copy(
                        isLoading = true,
                    )
                }
                is Result.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        statusMessage = null,
                        tajweed = result.data
                    )
                }
            }
        }
    }
}