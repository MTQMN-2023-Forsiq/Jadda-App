package unsiq.mtqmn23.jadda.domain.repository

import kotlinx.coroutines.flow.Flow
import unsiq.mtqmn23.jadda.util.Result

interface AuthRepository {
    fun register(name: String, email: String, password: String): Flow<Result<String?>>
    fun login(email: String, password: String): Flow<Result<String?>>
    fun getLoginState(): Boolean
    fun logout()
}