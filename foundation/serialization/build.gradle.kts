plugins {
    id("dev.srsouza.pokedex.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
    }
}