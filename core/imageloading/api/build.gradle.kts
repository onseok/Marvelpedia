plugins {
    id("marvelpedia.android.library")
    id("marvelpedia.android.compose")
}

android {
    namespace = "com.onseok.marvelpedia.core.imageloading"
}

dependencies {
    implementation(libs.kotlin.stdlib)

    implementation(libs.compose.foundation)
    implementation(libs.coil.compose)
}
