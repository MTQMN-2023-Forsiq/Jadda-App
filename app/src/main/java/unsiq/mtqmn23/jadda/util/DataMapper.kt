package unsiq.mtqmn23.jadda.util

import unsiq.mtqmn23.jadda.data.source.remote.response.DataSalatItemResponse
import unsiq.mtqmn23.jadda.data.source.remote.response.MovementAngleResponse
import unsiq.mtqmn23.jadda.data.source.remote.response.TajweedContentItemResponse
import unsiq.mtqmn23.jadda.data.source.remote.response.TajweedDataItemResponse
import unsiq.mtqmn23.jadda.domain.model.salat.DataSalatItem
import unsiq.mtqmn23.jadda.domain.model.salat.MovementAngle
import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedContentItem
import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedDataItem
import unsiq.mtqmn23.jadda.presentation.screen.salatpractice.model.PersonBodyAngle

fun TajweedDataItemResponse.toDomain(): TajweedDataItem {
    return TajweedDataItem(
        contents = contents.map(TajweedContentItemResponse::toDomain),
        icon = icon,
        id = id,
        title = title,
    )
}

fun TajweedContentItemResponse.toDomain(): TajweedContentItem {
    return TajweedContentItem(
        name = name,
        description = description,
        audioUrl = audioUrl,
        id = id,
        exampleUrl = exampleUrl,
        tajweedLetterUrl = tajweedLetterUrl
    )
}

fun List<DataSalatItemResponse>.toDomain(): List<DataSalatItem>  {
    return map {
        DataSalatItem(
            imageUrl = it.imageUrl,
            movementAngle = it.movementAngle.toDomain(),
            description = it.description,
            id = it.id,
            title = it.title,
        )
    }
}

private fun MovementAngleResponse.toDomain(): MovementAngle {
    return MovementAngle(
        rightKnee = rightKnee,
        leftAnkle = leftAnkle,
        rightHip = rightHip,
        leftWrist = leftWrist,
        leftHip = leftHip,
        rightWrist = rightWrist,
        rightAnkle = rightAnkle,
        rightElbow = rightElbow,
        leftKnee = leftKnee,
        leftShoulder = leftShoulder,
        leftElbow = leftElbow,
        rightShoulder = rightShoulder,
    )
}

fun MovementAngle.toPersonBodyAngle(): PersonBodyAngle {
    return PersonBodyAngle(
//        rightAnkle = rightAnkle ?: 0,
        rightElbow = rightElbow ?: 0,
        rightHip = rightHip ?: 0,
        rightKnee = rightKnee ?: 0,
        rightShoulder = rightShoulder ?: 0,
//        rightWrist = rightWrist ?: 0,
//        leftAnkle = leftAnkle ?: 0,
        leftElbow = leftElbow ?: 0,
        leftHip = leftHip ?: 0,
        leftKnee = leftKnee ?: 0,
        leftShoulder = leftShoulder ?: 0,
//        leftWrist = leftWrist ?: 0,
    )
}