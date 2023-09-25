package unsiq.mtqmn23.jadda.presentation.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import unsiq.mtqmn23.jadda.domain.repository.AuthRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnRegister -> {
                if (event.password != event.confirmationPassword) {
                    _state.update {
                        it.copy(
                            isError = true,
                            statusMessage = "Password Tidak Sama"
                        )
                    }
                    return
                }
                doRegister(event.name, event.email, event.password)
            }
            is RegisterEvent.OnValueChanged -> _state.update {
                it.copy(
                    name = event.name,
                    email = event.email,
                    password = event.password,
                    confirmationPassword = event.confirmationPassword,
                )
            }
            is RegisterEvent.ResetState -> _state.update {
                RegisterUiState()
            }
        }
    }

    private fun doRegister(name: String, email: String, password: String) = viewModelScope.launch {
        authRepository.register(name, email, password).collect { result ->
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
                        success = true,
                        statusMessage = result.data
                    )
                }
            }
        }
    }
}