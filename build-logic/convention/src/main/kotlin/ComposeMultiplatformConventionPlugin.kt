import com.android.build.api.dsl.ApplicationExtension
import dev.srsouza.convention.configureAndroid
import dev.srsouza.convention.configureAndroidCompose
import dev.srsouza.convention.configureKmp
import dev.srsouza.convention.configureKmpAndroid
import dev.srsouza.convention.configureKmpAutoManifest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class ComposeMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.compose")

            configureKmp()
            configureKmpAndroid()
            configureAndroid()
            configureAndroidCompose()
            configureKmpAutoManifest()
        }
    }

}