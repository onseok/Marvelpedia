plugins {
    id("marvelpedia.android.library")
    id("marvelpedia.android.hilt")
}

android {
    namespace = "com.onseok.marvelpedia.core.imageloading.impl"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        getByName("debug") {
            val filesAuthorityValue = "com.onseok.marvelpedia.debug.shareprovider"
            buildConfigField("String", "FILES_AUTHORITY", "\"$filesAuthorityValue\"")
            manifestPlaceholders["filesAuthority"] = filesAuthorityValue
        }
        getByName("release") {
            val filesAuthorityValue = "com.onseok.marvelpedia.shareprovider"
            buildConfigField("String", "FILES_AUTHORITY", "\"$filesAuthorityValue\"")
            manifestPlaceholders["filesAuthority"] = filesAuthorityValue
        }
    }
}

dependencies {
    implementation(projects.core.kotlin)
    implementation(projects.core.imageloading.api)
    implementation(libs.kotlin.stdlib)

    implementation(libs.androidx.startup)
    implementation(libs.coil.runtime)
}
