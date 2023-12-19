plugins {
    `kotlin-dsl`
}

group = "com.onseok.marvelpedia.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.android.pluginGradle)
    implementation(libs.kotlin.pluginGradle)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "marvelpedia.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "marvelpedia.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidTest") {
            id = "marvelpedia.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("androidHilt") {
            id = "marvelpedia.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidCompose") {
            id = "marvelpedia.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidBuildConfig") {
            id = "marvelpedia.android.buildconfig"
            implementationClass = "AndroidBuildConfigConventionPlugin"
        }
    }
}
