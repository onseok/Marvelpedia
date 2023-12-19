plugins {
    id("marvelpedia.android.buildconfig")
}

android {
    namespace = "com.onseok.marvelpedia.buildconfig"

    defaultConfig {
        buildConfigField("int", "VERSION_CODE", "Integer.valueOf(0)")
        buildConfigField("String", "VERSION_NAME", "\"0\"")
        buildConfigField("String", "API_BASE_URL", "\"stub\"")
    }
    buildFeatures {
        buildConfig = true
    }
}
