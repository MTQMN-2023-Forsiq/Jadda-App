package unsiq.mtqmn23.jadda.data.repository

import kotlinx.coroutines.flow.flow
import unsiq.mtqmn23.jadda.data.source.remote.RemoteDataSource
import unsiq.mtqmn23.jadda.data.source.remote.response.WatchDataItemResponse
import unsiq.mtqmn23.jadda.domain.repository.WatchRepository
import unsiq.mtqmn23.jadda.util.Result
import unsiq.mtqmn23.jadda.util.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : WatchRepository {

    override fun getVideos() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getVideos()
            val result = response.data.map(WatchDataItemResponse::toDomain)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }
}