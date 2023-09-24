package unsiq.mtqmn23.jadda.presentation.screen.tafsir

import unsiq.mtqmn23.jadda.domain.model.tafsir.TafsirDataItem

data class TafsirUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val statusMessage: String? = null,
    val tafsir: List<TafsirDataItem> = emptyList(),
)
