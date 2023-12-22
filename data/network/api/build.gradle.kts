plugins {
    id("marvelpedia.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.onseok.marvelpedia.data.network"
}

dependencies {
    implementation(projects.data.model)
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.serialization)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.androidx.core)
}
