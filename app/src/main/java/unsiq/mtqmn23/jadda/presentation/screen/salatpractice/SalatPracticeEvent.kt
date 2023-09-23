package unsiq.mtqmn23.jadda.presentation.screen.salatpractice

import unsiq.mtqmn23.jadda.domain.model.salat.DataSalatItem
import unsiq.mtqmn23.jadda.presentation.screen.salatpractice.model.PersonBodyAngle

sealed class SalatPracticeEvent {
    data class AddSalatItems(val salatItems: List<DataSalatItem>) : SalatPracticeEvent()
    data class SetScore(val actualAngle: PersonBodyAngle) : SalatPracticeEvent()
    object ResetScore : SalatPracticeEvent()
    data class OnPosePerfect(val currentPosePosition: Int) : SalatPracticeEvent()
    data class OnShowHideExitDialog(val isExitDialogShow: Boolean) : SalatPracticeEvent()
}