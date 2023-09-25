package unsiq.mtqmn23.jadda.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SalatResponse(

	@field:SerializedName("data")
	val data: List<DataSalatItemResponse>,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class MovementAngleResponse(

	@field:SerializedName("right_knee")
	val rightKnee: Int? = null,

	@field:SerializedName("left_ankle")
	val leftAnkle: Int? = null,

	@field:SerializedName("right_hip")
	val rightHip: Int? = null,

	@field:SerializedName("left_wrist")
	val leftWrist: Int? = null,

	@field:SerializedName("left_hip")
	val leftHip: Int? = null,

	@field:SerializedName("right_wrist")
	val rightWrist: Int? = null,

	@field:SerializedName("right_ankle")
	val rightAnkle: Int? = null,

	@field:SerializedName("right_elbow")
	val rightElbow: Int? = null,

	@field:SerializedName("left_knee")
	val leftKnee: Int? = null,

	@field:SerializedName("left_shoulder")
	val leftShoulder: Int? = null,

	@field:SerializedName("left_elbow")
	val leftElbow: Int? = null,

	@field:SerializedName("right_shoulder")
	val rightShoulder: Int? = null
)

data class DataSalatItemResponse(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("movement_angle")
	val movementAngle: MovementAngleResponse,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)
