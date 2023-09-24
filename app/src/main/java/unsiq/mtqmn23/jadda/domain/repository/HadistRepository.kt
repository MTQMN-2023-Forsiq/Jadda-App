package unsiq.mtqmn23.jadda.domain.repository

import kotlinx.coroutines.flow.Flow
import unsiq.mtqmn23.jadda.data.source.remote.response.HadistItemResponse
import unsiq.mtqmn23.jadda.domain.model.hadist.HadistItem
import unsiq.mtqmn23.jadda.util.Result

interface HadistRepository {
    fun getAllHadist(): Flow<Result<List<HadistItem>>>
}