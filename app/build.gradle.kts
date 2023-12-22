plugins {
    id("marvelpedia.android.application")
    id("marvelpedia.android.compose")
    id("marvelpedia.android.hilt")
    alias(libs.plugins.dependencyGuard)
}
if (file("google-services.json").exists()) {
    apply(plugin = "com.google.gms.google-services")
}

val useReleaseKeystore = rootProject.file("signing/app-release.jks").exists()

android {
    namespace = "com.onseok.marvelpedia"
    defaultConfig {
        versionCode = rootProject.extra["versionCode"] as Int
        versionName = rootProject.extra["versionName"] as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        maybeCreate("release").apply {
            if (useReleaseKeystore) {
                storeFile = rootProject.file("signing/app-release.jks")
                // TODO storePassword, keyAlias, keyPassword
            }
        }
    }
    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            if (useReleaseKeystore) {
                signingConfig = signingConfigs.getByName("release")
            } else {
                signingConfig = signingConfigs.getByName("debug")
            }
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    lint {
        checkReleaseBuilds = false
    }
}

dependencies {
    implementation(projects.core.kotlin)
    implementation(projects.core.designsystem)
    implementation(projects.core.logger)
    implementation(projects.data.repository.api)
    runtimeOnly(projects.data.network.impl)
    runtimeOnly(projects.core.imageloading.impl)
    runtimeOnly(projects.data.database.impl)
    runtimeOnly(projects.data.repository.impl)

    implementation(libs.kotlin.stdlib)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.startup)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.compiler)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.windowSizeClass)

    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.accompanist.drawablepainter)

    implementation(libs.androidx.profileinstaller)
}

dependencyGuard {
    configuration("releaseRuntimeClasspath")
}

tasks.register("applyDependencyBaseline") {
    dependsOn("dependencyGuardBaseline")
}
