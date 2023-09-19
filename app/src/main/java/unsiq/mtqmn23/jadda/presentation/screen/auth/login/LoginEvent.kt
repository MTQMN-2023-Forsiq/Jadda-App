package unsiq.mtqmn23.jadda.presentation.screen.auth.login

sealed class LoginEvent {
    data class OnLogin(
        val email: String,
        val password: String
    ) : LoginEvent()
    data class OnValueChanged(
        val email: String,
        val password: String
    ) : LoginEvent()
}
