package unsiq.mtqmn23.jadda.data.repository

import kotlinx.coroutines.flow.flow
import unsiq.mtqmn23.jadda.data.source.remote.RemoteDataSource
import unsiq.mtqmn23.jadda.data.source.remote.response.TajweedDataItemResponse
import unsiq.mtqmn23.jadda.domain.repository.TajweedRepository
import unsiq.mtqmn23.jadda.util.Result
import unsiq.mtqmn23.jadda.util.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TajweedRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : TajweedRepository {

    override fun getAllTajweed() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getAllTajweed()
            val result = response.tajweed.map(TajweedDataItemResponse::toDomain)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }

    override fun getTajweedById(id: String) = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getTajweedById(id)
            val result = response.data.toDomain()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }
}