plugins {
    id("dev.srsouza.pokedex.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(compose.material3)
        }
        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
        }
    }
}