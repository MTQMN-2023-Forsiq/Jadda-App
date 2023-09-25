package unsiq.mtqmn23.jadda.data.repository

import kotlinx.coroutines.flow.flow
import unsiq.mtqmn23.jadda.data.source.remote.RemoteDataSource
import unsiq.mtqmn23.jadda.domain.repository.HadistRepository
import unsiq.mtqmn23.jadda.util.Result
import unsiq.mtqmn23.jadda.util.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HadistRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
): HadistRepository {
    override fun getAllHadist() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getAllHadist()
            val result = response.data.toDomain()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }
}