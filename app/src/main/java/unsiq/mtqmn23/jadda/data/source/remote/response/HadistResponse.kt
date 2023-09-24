package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class HadistResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("hadiths")
	val hadiths: List<HadistItemResponse>,

	@field:SerializedName("requested")
	val requested: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("available")
	val availabl: Int? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class HadistItemResponse(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("arab")
	val arab: String? = null
)
