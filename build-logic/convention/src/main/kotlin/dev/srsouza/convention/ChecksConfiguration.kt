package dev.srsouza.convention

import org.gradle.api.Project

internal fun Project.applyKtLint() {
    plugins.apply("org.jlleitschuh.gradle.ktlint")
}