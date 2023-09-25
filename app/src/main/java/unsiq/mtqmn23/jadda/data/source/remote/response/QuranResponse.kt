package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class QuranResponse(

	@field:SerializedName("data")
	val data: List<QuranDataItemResponse>,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class QuranDataItemResponse(

	@field:SerializedName("ayat")
	val ayat: Int? = null,

	@field:SerializedName("revelation")
	val revelation: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("translation")
	val translation: String? = null,

	@field:SerializedName("short_name")
	val shortName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
