package unsiq.mtqmn23.jadda.domain.model.salat

data class DataSalatItem(
    val imageUrl: String? = null,
    val movementAngle: MovementAngle = MovementAngle(),
    val description: String? = null,
    val id: Int? = null,
    val title: String? = null
)

data class MovementAngle(
    val rightKnee: Int? = null,
    val leftAnkle: Int? = null,
    val rightHip: Int? = null,
    val leftWrist: Int? = null,
    val leftHip: Int? = null,
    val rightWrist: Int? = null,
    val rightAnkle: Int? = null,
    val rightElbow: Int? = null,
    val leftKnee: Int? = null,
    val leftShoulder: Int? = null,
    val leftElbow: Int? = null,
    val rightShoulder: Int? = null
)

