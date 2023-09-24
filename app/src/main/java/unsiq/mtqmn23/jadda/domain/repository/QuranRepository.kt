package unsiq.mtqmn23.jadda.domain.repository

import kotlinx.coroutines.flow.Flow
import unsiq.mtqmn23.jadda.domain.model.quran.DataSurah
import unsiq.mtqmn23.jadda.domain.model.quran.QuranDataItem
import unsiq.mtqmn23.jadda.util.Result

interface QuranRepository {
    fun getAllSurah(): Flow<Result<List<QuranDataItem>>>
    fun getDetailSurah(id: String): Flow<Result<DataSurah>>
}