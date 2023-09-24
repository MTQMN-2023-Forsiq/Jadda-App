package unsiq.mtqmn23.jadda.presentation.screen.compass

sealed class CompassEvent {
    data class OnStartStopObserving(
        val observing: Boolean
    ) : CompassEvent()
}