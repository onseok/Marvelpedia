import org.jetbrains.kotlin.konan.properties.Properties

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

plugins {
    id("marvelpedia.android.buildconfig")
}

android {
    namespace = "com.onseok.marvelpedia.buildconfig"

    defaultConfig {
        buildConfigField("String", "API_BASE_URL", properties.getProperty("API_BASE_URL"))
        buildConfigField("String", "MARVEL_PUBLIC_KEY", properties.getProperty("MARVEL_PUBLIC_KEY"))
        buildConfigField("String", "MARVEL_PRIVATE_KEY", properties.getProperty("MARVEL_PRIVATE_KEY"))
    }
    buildFeatures {
        buildConfig = true
    }
}
