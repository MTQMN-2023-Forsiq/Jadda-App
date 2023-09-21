package unsiq.mtqmn23.jadda.presentation.screen.tajweeddetect

import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedContentItem

data class TajweedDetectUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val statusMessage: String? = null,
    val detailTajweed: TajweedContentItem = TajweedContentItem(),
    val isSheetOpen: Boolean = false,
)