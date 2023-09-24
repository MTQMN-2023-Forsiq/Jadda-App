package unsiq.mtqmn23.jadda.presentation.screen.watch

import unsiq.mtqmn23.jadda.domain.model.watch.WatchDataItem

data class WatchUiState(
    val isLoading: Boolean = false,
    val statusMessage: String? = null,
    val videoItems: List<WatchDataItem> = emptyList(),
)