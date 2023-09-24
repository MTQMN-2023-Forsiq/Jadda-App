package unsiq.mtqmn23.jadda.presentation.screen.quran

import unsiq.mtqmn23.jadda.domain.model.quran.QuranDataItem

data class QuranUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val statusMessage: String? = null,
    val surah: List<QuranDataItem> = emptyList(),
)
