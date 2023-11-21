package dev.srsouza.convention

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

internal fun Project.configureKmp() {
    extensions.configure<KotlinMultiplatformExtension> {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "17"
                }
            }
        }
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        )

        sourceSets {
            all {
                languageSettings {
                    optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
                }
            }
        }
    }
}

private fun KotlinMultiplatformExtension.sourceSets(setup: NamedDomainObjectContainer<KotlinSourceSet>.() -> Unit) {
    (this as ExtensionAware).extensions.configure<NamedDomainObjectContainer<KotlinSourceSet>> {
        setup()
    }
}