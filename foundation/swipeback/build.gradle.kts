plugins {
    id("dev.srsouza.pokedex.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.material)
            implementation(compose.foundation)
            api(libs.voyager.navigator)
        }
    }
}