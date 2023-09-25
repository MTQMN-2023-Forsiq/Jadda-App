package unsiq.mtqmn23.jadda.presentation.screen.tafsir

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.TafsirRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject

@HiltViewModel
class TafsirViewModel @Inject constructor(
    private val tafsirRepository: TafsirRepository,
): ViewModel() {

    private val _state = MutableStateFlow(TafsirUiState())
    val state = _state.asStateFlow()

    init {
        getAllTafsir()
    }

    private fun getAllTafsir() = viewModelScope.launch {
        tafsirRepository.getAllTafsir().collect{result ->
            when(result){
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
                        tafsir = result.data
                    )
                }
            }
        }
    }
}