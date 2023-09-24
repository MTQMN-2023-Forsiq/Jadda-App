package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("data")
	val data: ProfileItemResponse,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: Any? = null
)

data class ProfileItemResponse(

	@field:SerializedName("task_complete")
	val taskComplete: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("ranking")
	val ranking: Int? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("point")
	val point: Int? = null
)
