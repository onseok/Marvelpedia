plugins {
    id("marvelpedia.android.library")
}

android {
    namespace = "com.onseok.marvelpedia.data.repository"
}

dependencies {
    implementation(projects.data.model)
    implementation(libs.kotlin.stdlib)
    implementation(libs.coroutines.core)
}
