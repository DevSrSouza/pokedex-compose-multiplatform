plugins {
    id("dev.srsouza.pokedex.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(compose.material3)
            api(projects.foundation.error)
            api(libs.composeIcons.tablerIcons)
        }
    }
}