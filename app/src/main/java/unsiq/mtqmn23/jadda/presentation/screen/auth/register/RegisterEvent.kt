package unsiq.mtqmn23.jadda.presentation.screen.auth.register

sealed class RegisterEvent {
    data class OnRegister(
        val name: String,
        val email: String,
        val password: String,
        val confirmationPassword: String,
    ) : RegisterEvent()
    data class OnValueChanged(
        val name: String,
        val email: String,
        val password: String,
        val confirmationPassword: String,
    ) : RegisterEvent()
    object ResetState : RegisterEvent()
}
