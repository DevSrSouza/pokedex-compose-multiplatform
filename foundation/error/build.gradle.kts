plugins {
    id("dev.srsouza.pokedex.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(compose.foundation)
            implementation(libs.composeIcons.tablerIcons)
        }
    }
}