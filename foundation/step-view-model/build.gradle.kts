plugins {
    id("dev.srsouza.pokedex.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.foundation.error)
            api(projects.foundation.core)
            api(libs.kotlinx.coroutines.core)
            api(libs.kodein.core)
        }
        androidMain.dependencies {
            api(libs.kotlinx.coroutines.android)
        }
    }
}