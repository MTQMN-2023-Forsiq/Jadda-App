package unsiq.mtqmn23.jadda.presentation.screen.hadist

import unsiq.mtqmn23.jadda.domain.model.hadist.DataHadist

data class HadistUiState(
    val isLoading: Boolean = false,
    val statusMessage: String? = null,
    val dataHadist: DataHadist = DataHadist()
)
