package unsiq.mtqmn23.jadda.presentation.screen.auth.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val statusMessage: String? = null,
    val email: String = "",
    val password: String = "",
)
