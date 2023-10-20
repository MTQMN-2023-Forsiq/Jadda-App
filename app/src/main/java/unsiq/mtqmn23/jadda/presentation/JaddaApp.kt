package unsiq.mtqmn23.jadda.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import unsiq.mtqmn23.jadda.BuildConfig
import unsiq.mtqmn23.jadda.util.ReleaseTree

@HiltAndroidApp
class JaddaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}