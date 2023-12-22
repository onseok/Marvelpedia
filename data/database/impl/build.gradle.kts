plugins {
    id("marvelpedia.android.library")
    id("marvelpedia.android.hilt")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.onseok.marvelpedia.data.database.impl"
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.incremental", "true")
            }
        }
    }
}

dependencies {
    implementation(projects.core.kotlin)
    implementation(projects.core.logger)
    implementation(projects.data.model)
    implementation(projects.data.database.api)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.serialization)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}