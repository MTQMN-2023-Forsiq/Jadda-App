package unsiq.mtqmn23.jadda.domain.model.tajweed

data class TajweedDataItem(
    val contents: List<TajweedContentItem>,
    val icon: String? = null,
    val id: Int? = null,
    val title: String? = null
)