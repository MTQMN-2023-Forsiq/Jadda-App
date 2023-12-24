package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RankingResponse(

	@field:SerializedName("data")
	val data: List<RankingItemResponse>,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class RankingItemResponse(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rangking")
	val rangking: Int? = null,

	@field:SerializedName("point")
	val point: String? = null
)
