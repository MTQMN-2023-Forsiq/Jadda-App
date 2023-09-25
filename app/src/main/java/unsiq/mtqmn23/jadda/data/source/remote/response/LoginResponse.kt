package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val loginData: LoginDataResponse? = null,

	@field:SerializedName("error")
	val error: Boolean = false,

	@field:SerializedName("message")
	val message: String? = null
)

data class LoginDataResponse(

	@field:SerializedName("user")
	val user: UserResponse? = null,

	@field:SerializedName("token")
	val token: String? = null
)

data class UserResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
