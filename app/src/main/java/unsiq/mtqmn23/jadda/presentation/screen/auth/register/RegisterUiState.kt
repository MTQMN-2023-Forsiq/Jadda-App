package unsiq.mtqmn23.jadda.presentation.screen.auth.register

data class RegisterUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val statusMessage: String? = null,
    val success: Boolean = false,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
)