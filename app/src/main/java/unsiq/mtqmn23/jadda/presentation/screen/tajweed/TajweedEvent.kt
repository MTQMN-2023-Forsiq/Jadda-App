package unsiq.mtqmn23.jadda.presentation.screen.tajweed

sealed class TajweedEvent {
    data class OnInitial(
        val id: String
    ) : TajweedEvent()
}
