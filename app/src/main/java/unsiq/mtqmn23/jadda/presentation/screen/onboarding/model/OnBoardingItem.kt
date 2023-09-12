package unsiq.mtqmn23.jadda.presentation.screen.onboarding.model

import unsiq.mtqmn23.jadda.R

data class OnBoardingItem(
    val title: String,
    val description: String,
    val image: Int
)

object OnBoardingData {
    val onBoardingItems = listOf(
        OnBoardingItem(
            title = "Belajar Tajwid",
            description = "Pelajari ilmu tajwid dengan materi yang lengkap dan mudah untuk dipahami.",
            image = R.drawable.ic_onboarding_image_1
        ),
        OnBoardingItem(
            title = "Materi Sholat",
            description = "Pahami kajian materi lengkap tentang sholat. Terdapat juga fitur gerakan sholat.",
            image = R.drawable.ic_onboarding_image_2
        ),
        OnBoardingItem(
            title = "Baca Al-Qur’an",
            description = "Mendapatkan banyak pahala dengan melakukan ibadah membaca Al-Qur’an.",
            image = R.drawable.ic_onboarding_image_3
        ),
    )
}