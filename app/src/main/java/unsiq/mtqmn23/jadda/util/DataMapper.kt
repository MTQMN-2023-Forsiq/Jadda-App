package unsiq.mtqmn23.jadda.util

import unsiq.mtqmn23.jadda.data.source.remote.response.TajweedContentItemResponse
import unsiq.mtqmn23.jadda.data.source.remote.response.TajweedDataItemResponse
import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedContentItem
import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedDataItem

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