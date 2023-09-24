package unsiq.mtqmn23.jadda.presentation.screen.watch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.WatchRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject

@HiltViewModel
class WatchViewModel @Inject constructor(
    private val watchRepository: WatchRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(WatchUiState())
    val state = _state.asStateFlow()

    init {
        getAllVideos()
    }

    private fun getAllVideos() = viewModelScope.launch {
        watchRepository.getVideos().collect { result ->
            when (result) {
                is Result.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        statusMessage = result.message
                    )
                }
                is Result.Loading -> _state.update {
                    it.copy(
                        isLoading = true,
                        statusMessage = null,
                    )
                }
                is Result.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        statusMessage = null,
                        videoItems = result.data
                    )
                }
            }
        }
    }
}