plugins {
    id("dev.srsouza.pokedex.multiplatform")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.ktorfit.core)
            implementation(libs.ktor.core)
            implementation(projects.foundation.core)
            implementation(projects.foundation.serialization)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.contentNegotiation)
            implementation(libs.ktor.logging)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}