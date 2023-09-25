package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailTajweedResponse(

	@field:SerializedName("data")
	val data: TajweedContentItemResponse,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

