plugins {
    id("dev.srsouza.pokedex.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.foundation)
            api(libs.voyager.navigator)
            implementation(libs.kodein.compose)
            implementation(libs.kodein.core)
        }
    }
}