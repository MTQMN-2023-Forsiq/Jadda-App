package unsiq.mtqmn23.jadda.presentation.screen.hadist

import unsiq.mtqmn23.jadda.domain.model.hadist.HadistItem
import unsiq.mtqmn23.jadda.domain.model.quran.QuranDataItem

data class HadistUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val statusMessage: String? = null,
    val hadist: List<HadistItem> = emptyList(),
)
