package unsiq.mtqmn23.jadda.presentation.screen.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedDataItem

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val statusMessage: String? = null,
    val listTajweed: SnapshotStateList<TajweedDataItem> = mutableStateListOf(),
    val expandableCardIds: SnapshotStateList<Int> = mutableStateListOf(),
)
