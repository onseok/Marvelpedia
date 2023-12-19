import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import com.onseok.marvelpedia.buildlogic.configureAndroid
import com.onseok.marvelpedia.buildlogic.configureKotlin
import com.onseok.marvelpedia.buildlogic.implementation
import com.onseok.marvelpedia.buildlogic.project

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            configureAndroid()
            configureKotlin()

            dependencies {
                implementation(project(path = ":core:buildconfig"))
            }
        }
    }
}
