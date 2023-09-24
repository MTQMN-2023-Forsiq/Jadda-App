package unsiq.mtqmn23.jadda.presentation.screen.hadist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.HadistRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject

@HiltViewModel
class HadistViewModel @Inject constructor(
    private val hadistRepository: HadistRepository,
): ViewModel() {
    private val _state = MutableStateFlow(HadistUiState())
    val state = _state.asStateFlow()

    init {
        getAllHadist()
    }

    private fun getAllHadist() = viewModelScope.launch {
        hadistRepository.getAllHadist().collect { result ->
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
                    )
                }
                is Result.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        hadist = result.data
                    )
                }
            }
        }
    }
}