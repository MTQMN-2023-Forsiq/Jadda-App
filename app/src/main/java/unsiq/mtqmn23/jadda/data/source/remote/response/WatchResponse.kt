package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class WatchResponse(

	@field:SerializedName("data")
	val data: List<WatchDataItemResponse>,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class WatchDataItemResponse(

	@field:SerializedName("video_url")
	val videoUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("info")
	val info: String? = null
)
