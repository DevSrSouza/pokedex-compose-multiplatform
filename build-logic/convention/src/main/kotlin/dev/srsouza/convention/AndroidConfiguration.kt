@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
package dev.srsouza.convention

import com.android.build.gradle.BaseExtension
import dev.srsouza.convention.tasks.GenerateManifestTask
import dev.srsouza.convention.tasks.GenerateManifestTask.Companion.pathSuffixFor
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.jetbrains.compose.ComposeCompilerCompatibility
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

internal fun Project.configureAndroid() {
    configure<BaseExtension> {
        val suffix = pathSuffixFor(
            rootProjectPath = rootProject.path,
            currentProjectPath = path,
            replaceDashesWithDot = provider { true },
        )
        namespace = "dev.srsouza.pokedex.$suffix"

        compileSdkVersion(34)

        defaultConfig {
            minSdk = 24
            targetSdk = 34

            versionCode = 1
            versionName = "1.0.0"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}

internal fun Project.configureAndroidCompose() {
    configure<BaseExtension> {
        buildFeatures.compose = true
        composeOptions {
            //kotlinCompilerExtensionVersion = ComposeCompilerCompatibility.compilerVersionFor(getKotlinPluginVersion())
            kotlinCompilerExtensionVersion = "1.5.10"
        }
    }
}

internal fun Project.configureKmpAndroid() {
    configure<BaseExtension> {
        sourceSets["main"].apply {
            manifest.srcFile(GenerateManifestTask.GENERATED_MANIFEST_PATH)
            res.srcDirs("src/androidMain/resources")
            resources.srcDirs("src/commonMain/resources")
        }
    }
}