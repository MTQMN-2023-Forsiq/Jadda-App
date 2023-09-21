package unsiq.mtqmn23.jadda.data.source.remote.retrofit

import okhttp3.Interceptor
import okhttp3.Response
import unsiq.mtqmn23.jadda.data.source.local.sharedpref.SessionManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val sessionManager: SessionManager,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}