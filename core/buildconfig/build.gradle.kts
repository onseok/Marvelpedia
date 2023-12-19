plugins {
    id("marvelpedia.android.buildconfig")
}

android {
    namespace = "com.onseok.marvelpedia.buildconfig"

    defaultConfig {
        buildConfigField("int", "VERSION_CODE", "Integer.valueOf(${rootProject.extra["versionCode"]})")
        buildConfigField("String", "VERSION_NAME", "\"${rootProject.extra["versionName"]}\"")
    }
    buildFeatures {
        buildConfig = true
    }
}
