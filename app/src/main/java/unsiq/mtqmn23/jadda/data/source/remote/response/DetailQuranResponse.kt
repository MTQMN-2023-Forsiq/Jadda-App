package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailQuranResponse(

	@field:SerializedName("data")
	val data: DataSurahResponse,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataSurahResponse(

	@field:SerializedName("ayat")
	val ayat: Int? = null,

	@field:SerializedName("revelation")
	val revelation: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("short_name")
	val shortName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("verses")
	val verses: List<VersesItemResponse>
)

data class VersesItemResponse(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("translation")
	val translation: String? = null,

	@field:SerializedName("text_arab")
	val textArab: String? = null,

	@field:SerializedName("audio")
	val audio: String? = null
)
