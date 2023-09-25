package unsiq.mtqmn23.jadda.domain.model.quran

data class DataSurah(
    val ayat: Int? = null,
    val revelation: String? = null,
    val name: String? = null,
    val shortName: String? = null,
    val id: Int? = null,
    val verses: List<VersesItem> = emptyList()
)

data class VersesItem(
    val number: Int? = null,
    val translation: String? = null,
    val textArab: String? = null,
    val audio: String? = null
)
