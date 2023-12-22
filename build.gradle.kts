@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.jvm).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.dagger.hilt).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.dependencyGuard).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
}

apply {
    from("$rootDir/gradle/version.gradle")
}
