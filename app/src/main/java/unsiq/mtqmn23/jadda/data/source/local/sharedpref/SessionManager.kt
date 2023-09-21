package unsiq.mtqmn23.jadda.data.source.local.sharedpref

interface SessionManager {
    fun saveAuthToken(token: String)
    fun fetchAuthToken(): String?
    fun saveLoginState(isLogin: Boolean)
    fun logout()
}