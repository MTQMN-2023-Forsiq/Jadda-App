package unsiq.mtqmn23.jadda.presentation.screen.profile

import unsiq.mtqmn23.jadda.domain.model.profile.ProfileItem

data class ProfileUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val statusMessage: String? = null,
    val profile: ProfileItem = ProfileItem(),
    val isLogout: Boolean = false,
    val isAlertShown: Boolean = false,
)
