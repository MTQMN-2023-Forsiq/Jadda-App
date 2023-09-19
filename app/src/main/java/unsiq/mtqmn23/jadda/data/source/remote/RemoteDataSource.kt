package unsiq.mtqmn23.jadda.data.source.remote

import unsiq.mtqmn23.jadda.data.source.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun register(name: String, email: String, password: String) = apiService.register(name, email, password)

    suspend fun login(email: String, password: String) = apiService.login(email, password)

    suspend fun getAllTajweed() = apiService.getAllTajweed()
}