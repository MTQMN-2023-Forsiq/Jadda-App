package unsiq.mtqmn23.jadda.presentation.screen.leaderboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.ProfileRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LeaderboardState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            profileRepository.getRanking().collect { result ->
                when (result) {
                    is Result.Error -> _state.update {
                        it.copy(
                            isError = true,
                            isLoading = false,
                            statusMessage = result.message
                        )
                    }
                    is Result.Loading -> _state.update {
                        it.copy(
                            isError = false,
                            isLoading = true
                        )
                    }
                    is Result.Success -> _state.update {
                        it.copy(
                            isError = false,
                            isLoading = false,
                            results = result.data
                        )
                    }
                }
            }
        }
    }
}