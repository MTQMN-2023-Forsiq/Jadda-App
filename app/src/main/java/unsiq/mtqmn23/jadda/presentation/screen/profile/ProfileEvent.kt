package unsiq.mtqmn23.jadda.presentation.screen.profile

sealed class ProfileEvent {
    object OnLogoutConfirmed : ProfileEvent()
    data class OnShowHideAlert(
        val isShown: Boolean
    ) : ProfileEvent()
}