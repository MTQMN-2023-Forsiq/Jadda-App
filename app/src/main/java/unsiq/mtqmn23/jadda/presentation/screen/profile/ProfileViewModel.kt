package unsiq.mtqmn23.jadda.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.AuthRepository
import unsiq.mtqmn23.jadda.domain.repository.ProfileRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
): ViewModel() {

    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        getProfile()
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnLogoutConfirmed -> _state.update {
                authRepository.logout()
                it.copy(
                    isLogout = true,
                    isAlertShown = false,
                )
            }
            is ProfileEvent.OnShowHideAlert -> _state.update {
                it.copy(
                    isAlertShown = event.isShown
                )
            }
        }
    }

    private fun getProfile() = viewModelScope.launch {
        profileRepository.getProfile().collect{result ->
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
                        profile = result.data
                    )
                }
            }
        }
    }

}