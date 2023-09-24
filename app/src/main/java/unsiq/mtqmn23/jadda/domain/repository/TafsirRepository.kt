package unsiq.mtqmn23.jadda.domain.repository

import kotlinx.coroutines.flow.Flow
import unsiq.mtqmn23.jadda.domain.model.tafsir.TafsirDataItem
import unsiq.mtqmn23.jadda.util.Result

interface TafsirRepository {
    fun getAllTafsir(): Flow<Result<List<TafsirDataItem>>>
}