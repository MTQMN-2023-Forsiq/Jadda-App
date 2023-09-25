package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SalatScheduleResponse(

	@field:SerializedName("data")
	val data: DateResponse,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class TimesResponse(

	@field:SerializedName("Sunset")
	val sunset: String? = null,

	@field:SerializedName("Asr")
	val asr: String? = null,

	@field:SerializedName("Isha")
	val isha: String? = null,

	@field:SerializedName("Fajr")
	val fajr: String? = null,

	@field:SerializedName("Dhuhr")
	val dhuhr: String? = null,

	@field:SerializedName("Maghrib")
	val maghrib: String? = null,

	@field:SerializedName("Lastthird")
	val lastthird: String? = null,

	@field:SerializedName("Firstthird")
	val firstthird: String? = null,

	@field:SerializedName("Sunrise")
	val sunrise: String? = null,

	@field:SerializedName("Midnight")
	val midnight: String? = null,

	@field:SerializedName("Imsak")
	val imsak: String? = null
)

data class DateResponse(

	@field:SerializedName("national_date")
	val nationalDate: String? = null,

	@field:SerializedName("hijriah_date")
	val hijriahDate: String? = null,

	@field:SerializedName("times")
	val times: TimesResponse
)
