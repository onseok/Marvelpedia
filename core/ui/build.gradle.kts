plugins {
    id("marvelpedia.android.library")
    id("marvelpedia.android.compose")
}

android {
    namespace = "com.onseok.marvelpedia.core.ui"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.resources)
    implementation(projects.data.model)
    implementation(projects.core.imageloading.api)

    implementation(libs.kotlin.stdlib)

    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.materialIconsExtended)
    implementation(libs.compose.ui)

    implementation(libs.androidx.window)
}
