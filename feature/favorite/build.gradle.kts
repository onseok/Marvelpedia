plugins {
    id("marvelpedia.android.library")
    id("marvelpedia.android.compose")
    id("marvelpedia.android.hilt")
}

android {
    namespace = "com.onseok.marvelpedia.feature.favorite"
}

dependencies {
    implementation(projects.core.kotlin)
    implementation(projects.core.designsystem)
    implementation(projects.core.imageloading.api)
    implementation(projects.core.logger)
    implementation(projects.core.resources)
    implementation(projects.data.repository.api)
    implementation(projects.data.model)

    implementation(libs.kotlin.stdlib)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.compose.animation.graphics)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
}
