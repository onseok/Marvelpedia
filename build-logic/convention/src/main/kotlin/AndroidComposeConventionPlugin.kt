import org.gradle.api.Plugin
import org.gradle.api.Project
import com.onseok.marvelpedia.buildlogic.configureCompose

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureCompose()
        }
    }
}
