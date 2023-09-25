package unsiq.mtqmn23.jadda.domain.repository

import kotlinx.coroutines.flow.Flow
import unsiq.mtqmn23.jadda.domain.model.profile.ProfileItem
import unsiq.mtqmn23.jadda.util.Result

interface ProfileRepository {
    fun getProfile(): Flow<Result<ProfileItem>>
}