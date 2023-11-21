package dev.srsouza.convention

import com.android.build.gradle.tasks.GenerateBuildConfig
import com.android.build.gradle.tasks.ManifestProcessorTask
import com.android.build.gradle.tasks.MergeResources
import dev.srsouza.convention.tasks.GenerateManifestTask
import dev.srsouza.convention.tasks.GenerateManifestTask.Companion.generateManifest
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import java.io.File

internal fun Project.configureKmpAutoManifest() {
    if (project.isSyncing()) {
        project.forceGenerateDuringSync()
    }

    val generateManifest = project.registerGenerateManifest()
    tasks.withType<GenerateBuildConfig>().configureEach {
        mustRunAfter(generateManifest)
    }
    tasks.withType<MergeResources>().configureEach {
        dependsOn(generateManifest)
    }
    tasks.withType<ManifestProcessorTask>().configureEach {
        dependsOn(generateManifest)
    }
}

private fun Project.registerGenerateManifest() =
    tasks.register<GenerateManifestTask>("generateAndroidManifest")

private fun Project.isSyncing() = hasProperty("android.injected.invoked.from.ide")

private fun Project.forceGenerateDuringSync() {
    if (manifestFile.exists()) return

    afterEvaluate {
        generateManifest(manifestFile)
    }
}

private val Project.manifestFile
    get() = File(buildDir, GenerateManifestTask.GENERATED_MANIFEST_PATH)