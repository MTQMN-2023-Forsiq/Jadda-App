package unsiq.mtqmn23.jadda.presentation.screen.salat

import unsiq.mtqmn23.jadda.domain.model.salat.DataSalatItem

data class SalatUiState(
    val isLoading: Boolean = false,
    val statusMessage: String? = null,
    val lisSalat: List<DataSalatItem> = emptyList(),
)
