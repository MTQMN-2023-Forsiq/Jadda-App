package unsiq.mtqmn23.jadda.data.source.remote

import unsiq.mtqmn23.jadda.data.source.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun register(name: String, email: String, password: String) = apiService.register(name, email, password)

    suspend fun login(email: String, password: String) = apiService.login(email, password)

    suspend fun getAllTajweed() = apiService.getAllTajweed()

    suspend fun getTajweedById(id: String) = apiService.getTajweedById(id)

    suspend fun getSalat() = apiService.getSalat()

    suspend fun getSalatSchedule(city: String) = apiService.getSalatSchedule(city)

    suspend fun getAllSurah() = apiService.getAllSurah()

    suspend fun getVideos() = apiService.getVideos()

    suspend fun getDetailSurah(id: String) = apiService.getDetailSurah(id)

    suspend fun getAllHadist() = apiService.getAllHadist()
    suspend fun getAllTafsir() = apiService.getAllTafsir()
}