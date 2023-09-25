package unsiq.mtqmn23.jadda.presentation.screen.auth.login

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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLogin -> {
                doLogin(event.email, event.password)
            }
            is LoginEvent.OnValueChanged -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        password = event.password
                    )
                }
            }
            is LoginEvent.ResetState -> _state.update {
                LoginUiState()
            }
        }
    }

    private fun doLogin(email: String, password: String) = viewModelScope.launch {
        authRepository.login(email, password).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            statusMessage = result.message
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true,
                            isError = false,
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = false,
                            loginSuccess = true,
                            statusMessage = result.data
                        )
                    }
                }
            }
        }
    }
}