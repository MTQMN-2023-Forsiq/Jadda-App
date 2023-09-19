package unsiq.mtqmn23.jadda.domain.repository

import kotlinx.coroutines.flow.Flow
import unsiq.mtqmn23.jadda.domain.model.tajweed.TajweedDataItem
import unsiq.mtqmn23.jadda.util.Result

interface TajweedRepository {
    fun getAllTajweed(): Flow<Result<List<TajweedDataItem>>>
}