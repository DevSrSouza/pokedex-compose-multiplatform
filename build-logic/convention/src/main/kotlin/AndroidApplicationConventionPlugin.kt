import com.android.build.api.dsl.ApplicationExtension
import dev.srsouza.convention.configureAndroid
import dev.srsouza.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")

            configureAndroid()
            configureAndroidCompose()
        }
    }

}