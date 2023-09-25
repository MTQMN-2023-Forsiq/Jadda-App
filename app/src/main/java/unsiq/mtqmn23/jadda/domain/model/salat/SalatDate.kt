package unsiq.mtqmn23.jadda.domain.model.salat

data class SalatTimes(
    val sunset: String? = null,
    val asr: String? = null,
    val isha: String? = null,
    val fajr: String? = null,
    val dhuhr: String? = null,
    val maghrib: String? = null,
    val lastthird: String? = null,
    val firstthird: String? = null,
    val sunrise: String? = null,
    val midnight: String? = null,
    val imsak: String? = null
)

data class SalatDate(
    val nationalDate: String? = null,
    val hijriahDate: String? = null,
    val times: SalatTimes = SalatTimes(),
)