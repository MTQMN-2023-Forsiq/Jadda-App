package unsiq.mtqmn23.jadda.data.repository

import kotlinx.coroutines.flow.flow
import unsiq.mtqmn23.jadda.data.source.remote.RemoteDataSource
import unsiq.mtqmn23.jadda.data.source.remote.response.QuranDataItemResponse
import unsiq.mtqmn23.jadda.domain.repository.QuranRepository
import unsiq.mtqmn23.jadda.util.Result
import unsiq.mtqmn23.jadda.util.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuranRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : QuranRepository {

    override fun getAllSurah() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getAllSurah()
            val result = response.data.map(QuranDataItemResponse::toDomain)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }

    override fun getDetailSurah(id: String) = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getDetailSurah(id)
            val result = response.data.toDomain()
            emit(Result.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }
}