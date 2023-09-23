package unsiq.mtqmn23.jadda.data.source.local.sharedpref

import android.content.SharedPreferences
import androidx.core.content.edit
import unsiq.mtqmn23.jadda.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManagerImpl @Inject constructor(
    private val prefs: SharedPreferences,
) : SessionManager {

    override fun saveAuthToken(token: String) {
        prefs.edit {
            putString(Constants.USER_TOKEN, token)
        }
    }

    override fun fetchAuthToken(): String? {
        return prefs.getString(Constants.USER_TOKEN, null)
    }

    override fun saveLoginState(isLogin: Boolean) {
        prefs.edit {
            putBoolean(Constants.LOGIN_STATE, isLogin)
        }
    }

    override fun getLoginState(): Boolean {
        return prefs.getBoolean(Constants.LOGIN_STATE, false)
    }

    override fun logout() {
        prefs.edit {
            clear()
        }
    }
}