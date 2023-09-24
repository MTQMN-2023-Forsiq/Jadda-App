package unsiq.mtqmn23.jadda.data.repository

import kotlinx.coroutines.flow.flow
import unsiq.mtqmn23.jadda.data.source.local.sharedpref.SessionManager
import unsiq.mtqmn23.jadda.data.source.remote.RemoteDataSource
import unsiq.mtqmn23.jadda.domain.repository.AuthRepository
import unsiq.mtqmn23.jadda.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val sessionManager: SessionManager,
) : AuthRepository {

    override fun register(name: String, email: String, password: String) = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.register(name, email, password)

            if (!response.error) {
                emit(Result.Success(response.message))
            } else {
                emit(Result.Error(response.message))
            }

        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }

    override fun login(email: String, password: String) = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.login(email, password)
            if (!response.error) {

                sessionManager.run {
                    saveAuthToken(response.loginData?.token.toString())
                    saveLoginState(isLogin = true)
                }

                emit(Result.Success(response.message ?: "Login Sukses"))
            } else {
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            emit(Result.Error("Terjadi Kesalahan"))
        }
    }

    override fun getLoginState(): Boolean {
        return sessionManager.getLoginState()
    }

    override fun logout() {
        sessionManager.logout()
    }
}