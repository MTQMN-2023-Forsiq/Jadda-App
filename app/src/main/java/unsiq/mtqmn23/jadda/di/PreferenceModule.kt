package unsiq.mtqmn23.jadda.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.data.source.local.sharedpref.SessionManager
import unsiq.mtqmn23.jadda.data.source.local.sharedpref.SessionManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    @Singleton
    abstract fun provideSessionManager(sessionManagerImpl: SessionManagerImpl): SessionManager

    companion object {
        @Provides
        @Singleton
        fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        }
    }
}