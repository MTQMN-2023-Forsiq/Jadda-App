package unsiq.mtqmn23.jadda.presentation.screen.leaderboard

import unsiq.mtqmn23.jadda.domain.model.ranking.RankingItem

data class LeaderboardState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val statusMessage: String? = null,
    val results: List<RankingItem> = emptyList()
)