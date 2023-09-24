package unsiq.mtqmn23.jadda.presentation.screen.quran.detail

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
class DetailSurahViewModel @Inject constructor(
    private val quranRepository: QuranRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(DetailSurahUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailSurahEvent) {
        when (event) {
            is DetailSurahEvent.OnInitial -> {
                getDetailSurah(event.id)
            }
        }
    }

    private fun getDetailSurah(id: String) = viewModelScope.launch {
        quranRepository.getDetailSurah(id).collect { result ->
            when (result) {
                is Result.Error -> _state.update {
                    it.copy(
                        statusMessage = result.message
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
                        dataSurah = result.data
                    )
                }
            }
        }
    }
}