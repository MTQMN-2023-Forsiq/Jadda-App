package unsiq.mtqmn23.jadda.presentation.screen.quran

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.QuranRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject

@HiltViewModel
class QuranViewModel @Inject constructor(
    private val quranRepository: QuranRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(QuranUiState())
    val state = _state.asStateFlow()

    init {
        getAllSurah()
    }

    private fun getAllSurah() = viewModelScope.launch {
        quranRepository.getAllSurah().collect { result ->
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
                        surah = result.data
                    )
                }
            }
        }
    }
}