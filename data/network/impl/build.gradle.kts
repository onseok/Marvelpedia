plugins {
    id("marvelpedia.android.library")
    id("marvelpedia.android.hilt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.onseok.marvelpedia.data.network.impl"
}

dependencies {
    implementation(projects.core.kotlin)
    implementation(projects.data.model)
    implementation(projects.data.network.api)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.serialization)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.androidx.core)

    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
}
