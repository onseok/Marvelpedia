import org.gradle.api.Plugin
import org.gradle.api.Project
import com.onseok.marvelpedia.buildlogic.configureAndroid
import com.onseok.marvelpedia.buildlogic.configureKotlin

class AndroidBuildConfigConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }
            configureAndroid()
            configureKotlin()
        }
    }
}
