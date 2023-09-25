package unsiq.mtqmn23.jadda.presentation.screen.tajweed

import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedContentItem

data class TajweedUiState(
    val isLoading: Boolean = false,
    val statusMessage: String? = null,
    val tajweed: TajweedContentItem = TajweedContentItem(),
)