plugins {
    id("marvelpedia.android.library")
    id("marvelpedia.android.hilt")
}

android {
    namespace = "com.onseok.marvelpedia.data.repository.impl"
}

dependencies {
    implementation(projects.core.kotlin)
    implementation(projects.core.logger)
    implementation(projects.data.model)
    implementation(projects.data.network.api)
    implementation(projects.data.database.api)
    implementation(projects.data.repository.api)
    implementation(projects.core.buildconfig)

    implementation(libs.kotlin.stdlib)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.androidx.core)
}
