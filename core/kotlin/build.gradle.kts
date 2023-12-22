plugins {
    id("marvelpedia.android.library")
    id("marvelpedia.android.hilt")
}

android {
    namespace = "com.onseok.marvelpedia.common"
}

dependencies {
    implementation(libs.coroutines.android)
}
