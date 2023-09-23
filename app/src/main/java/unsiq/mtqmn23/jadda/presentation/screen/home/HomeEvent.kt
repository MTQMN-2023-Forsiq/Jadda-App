package unsiq.mtqmn23.jadda.presentation.screen.home

sealed class HomeEvent {
    object Refresh : HomeEvent()
    data class OnTajweedCardClick(
        val id: Int
    ) : HomeEvent()
    object OnGetCurrentLocation : HomeEvent()
    data class OnGetSalatSchedule(
        val city: String
    ) : HomeEvent()
}
