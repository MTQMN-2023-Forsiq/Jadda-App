package unsiq.mtqmn23.jadda.presentation.screen.salat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.SalatRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject

@HiltViewModel
class SalatViewModel @Inject constructor(
    private val salatRepository: SalatRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SalatUiState())
    val state = _state.asStateFlow()

    init {
        getSalat()
    }

    private fun getSalat() = viewModelScope.launch {
        salatRepository.getSalat().collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            statusMessage = result.message
                        )
                    }
                }
                is Result.Loading -> {

                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            lisSalat = result.data
                        )
                    }
                }
            }
        }
    }
}