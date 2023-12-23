plugins {
    id("marvelpedia.android.library")
    id("marvelpedia.android.compose")
    id("marvelpedia.android.hilt")
}

android {
    namespace = "com.onseok.marvelpedia.feature.search"
}

dependencies {
    implementation(projects.feature.favorite)
    implementation(projects.core.kotlin)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.resources)
    implementation(projects.data.repository.api)
    implementation(projects.data.model)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.compose.animation.graphics)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}
