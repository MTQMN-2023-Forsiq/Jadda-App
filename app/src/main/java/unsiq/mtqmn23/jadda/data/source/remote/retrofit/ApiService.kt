package unsiq.mtqmn23.jadda.data.source.remote.retrofit

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import unsiq.mtqmn23.jadda.data.source.remote.response.DetailTajweedResponse
import unsiq.mtqmn23.jadda.data.source.remote.response.LoginResponse
import unsiq.mtqmn23.jadda.data.source.remote.response.RegisterResponse
import unsiq.mtqmn23.jadda.data.source.remote.response.SalatResponse
import unsiq.mtqmn23.jadda.data.source.remote.response.SalatScheduleResponse
import unsiq.mtqmn23.jadda.data.source.remote.response.TajweedResponse

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("tajweeds")
    suspend fun getAllTajweed(): TajweedResponse

    @GET("tajweed/{id}")
    suspend fun getTajweedById(
        @Path("id") id: String,
    ): DetailTajweedResponse

    @GET("sholat")
    suspend fun getSalat(): SalatResponse

    @GET("jadwal-sholat/{city}")
    suspend fun getSalatSchedule(
        @Path("city") city: String
    ): SalatScheduleResponse
}