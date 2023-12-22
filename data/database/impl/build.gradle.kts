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
    implementation(project(":core:kotlin"))
    implementation(project(":core:logger"))
    // TODO :data:model
    implementation(project(":data:database:api"))

    implementation(kotlin("stdlib"))
    implementation(kotlin("serialization"))
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}