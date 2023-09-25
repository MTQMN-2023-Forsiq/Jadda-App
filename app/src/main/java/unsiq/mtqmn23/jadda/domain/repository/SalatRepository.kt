package unsiq.mtqmn23.jadda.domain.repository

import kotlinx.coroutines.flow.Flow
import unsiq.mtqmn23.jadda.domain.model.salat.DataSalatItem
import unsiq.mtqmn23.jadda.domain.model.salat.SalatDate
import unsiq.mtqmn23.jadda.util.Result

interface SalatRepository {
    fun getSalat(): Flow<Result<List<DataSalatItem>>>
    fun getSalatSchedule(city: String): Flow<Result<SalatDate>>
}