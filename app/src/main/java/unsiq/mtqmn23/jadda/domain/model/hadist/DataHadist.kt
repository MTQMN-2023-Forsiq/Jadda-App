package unsiq.mtqmn23.jadda.domain.model.hadist

data class DataHadist(
    val id: String? = null,
    val name: String? = null,
    val available: Int? = null,
    val requested: Int? = null,
    val hadist: List<HadistItem> = emptyList()
)
