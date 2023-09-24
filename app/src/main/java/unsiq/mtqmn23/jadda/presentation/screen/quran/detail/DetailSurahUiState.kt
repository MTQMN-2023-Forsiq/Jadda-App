package unsiq.mtqmn23.jadda.presentation.screen.quran.detail

import unsiq.mtqmn23.jadda.domain.model.quran.DataSurah

data class DetailSurahUiState(
    val isLoading: Boolean = false,
    val statusMessage: String? = null,
    val dataSurah: DataSurah = DataSurah()
)
