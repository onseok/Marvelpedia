plugins {
    id("marvelpedia.android.library")
}

android {
    namespace = "com.onseok.marvelpedia.log"
}

dependencies {
    implementation(libs.timber)
}
