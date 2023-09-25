package unsiq.mtqmn23.jadda.data.repository

import kotlinx.coroutines.flow.flow
import unsiq.mtqmn23.jadda.data.source.remote.RemoteDataSource
import unsiq.mtqmn23.jadda.domain.repository.ProfileRepository
import unsiq.mtqmn23.jadda.util.Result
import unsiq.mtqmn23.jadda.util.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
): ProfileRepository {
    override fun getProfile() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getProfile()
            val result = response.data.toDomain()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }
}