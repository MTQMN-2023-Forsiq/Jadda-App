package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TajweedResponse(

	@field:SerializedName("data")
	val tajweed: List<TajweedDataItemResponse>,

	@field:SerializedName("error")
	val error: Boolean = false,

	@field:SerializedName("message")
	val message: String? = null
)

data class TajweedDataItemResponse(

	@field:SerializedName("contents")
	val contents: List<TajweedContentItemResponse>,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class TajweedContentItemResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("audio_url")
	val audioUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("example_url")
	val exampleUrl: String? = null,

	@field:SerializedName("tajweed_letter_url")
	val tajweedLetterUrl: String? = null
)
