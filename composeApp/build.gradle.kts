plugins {
    id("dev.srsouza.pokedex.compose")
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.foundation.navigation)
            implementation(projects.foundation.theme)
            implementation(projects.foundation.network)
            implementation(projects.foundation.serialization)
            implementation(libs.ktorfit.core)
            implementation(libs.lyricist.compose)

            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.kodein)
            implementation(libs.composeImageLoader)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kodein.core)
            implementation(libs.kodein.compose)
            implementation(libs.kodein.configurable)
            implementation(libs.composeIcons.tablerIcons)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activityCompose)
            implementation(libs.compose.uitooling)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

// ksp multiplatform workaround
dependencies {
    add("kspCommonMainMetadata", libs.lyricist.processor)
    add("kspCommonMainMetadata", libs.ktorfit.ksp)
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}

