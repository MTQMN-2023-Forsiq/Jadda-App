package unsiq.mtqmn23.jadda.presentation.screen.quran.detail

sealed class DetailSurahEvent {
    data class OnInitial(
        val id: String
    ) : DetailSurahEvent()
}