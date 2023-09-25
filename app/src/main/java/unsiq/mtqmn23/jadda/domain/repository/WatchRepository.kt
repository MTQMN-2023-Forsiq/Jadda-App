package unsiq.mtqmn23.jadda.domain.repository

import kotlinx.coroutines.flow.Flow
import unsiq.mtqmn23.jadda.domain.model.watch.WatchDataItem
import unsiq.mtqmn23.jadda.util.Result

interface WatchRepository {
    fun getVideos(): Flow<Result<List<WatchDataItem>>>
}