package unsiq.mtqmn23.jadda.presentation.screen.tajweeddetect

sealed class TajweedDetectEvent {
    data class OnBoundsClick(
        val tajweedId: String
    ) : TajweedDetectEvent()
    object OnDismissSheet: TajweedDetectEvent()
}
