package unsiq.mtqmn23.jadda.data.repository

import  kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import unsiq.mtqmn23.jadda.data.source.remote.RemoteDataSource
import unsiq.mtqmn23.jadda.domain.model.salat.DataSalatItem
import unsiq.mtqmn23.jadda.domain.model.salat.SalatDate
import unsiq.mtqmn23.jadda.domain.repository.SalatRepository
import unsiq.mtqmn23.jadda.util.Result
import unsiq.mtqmn23.jadda.util.toDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SalatRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : SalatRepository {

    override fun getSalat(): Flow<Result<List<DataSalatItem>>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getSalat()
            val result = response.data.toDomain()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }

    override fun getSalatSchedule(city: String): Flow<Result<SalatDate>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getSalatSchedule(city)
            val result = response.data.toDomain()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }
}