plugins {
    id("marvelpedia.android.library")
    id("marvelpedia.android.hilt")
    // https://github.com/Kotlin/kotlinx.serialization/issues?q=not+applied
    kotlin("plugin.serialization")
}

android {
    namespace = "com.onseok.marvelpedia.data.network.impl"
}

dependencies {
    implementation(projects.core.kotlin)
    implementation(projects.data.model)
    implementation(projects.data.network.api)
    implementation(projects.core.buildconfig)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.serialization)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.androidx.core)

    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)

    implementation(platform(libs.okhttp3.okhttp.bom))
    implementation(libs.okhttp3.okhttp)
    implementation(libs.okhttp3.logging.interceptor)
}
