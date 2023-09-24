package unsiq.mtqmn23.jadda.domain.repository

import kotlinx.coroutines.flow.Flow
import unsiq.mtqmn23.jadda.domain.model.hadist.DataHadist
import unsiq.mtqmn23.jadda.util.Result

interface HadistRepository {
    fun getAllHadist(): Flow<Result<DataHadist>>
}