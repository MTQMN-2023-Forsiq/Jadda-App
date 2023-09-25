package unsiq.mtqmn23.jadda.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import unsiq.mtqmn23.jadda.data.source.remote.RemoteDataSource
import unsiq.mtqmn23.jadda.data.source.remote.response.TafsirDataItemResponse
import unsiq.mtqmn23.jadda.domain.model.tafsir.TafsirDataItem
import unsiq.mtqmn23.jadda.domain.repository.TafsirRepository
import unsiq.mtqmn23.jadda.util.Result
import unsiq.mtqmn23.jadda.util.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TafsirRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
): TafsirRepository {
    override fun getAllTafsir(): Flow<Result<List<TafsirDataItem>>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getAllTafsir()
            val result = response.data.map(TafsirDataItemResponse::toDomain)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }
}