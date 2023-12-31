plugins {
    id("dev.srsouza.pokedex.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.material3)
            implementation(compose.foundation)
            implementation(projects.foundation.kodein)
            api(projects.foundation.stepViewModel)
            api(projects.foundation.components)
            api(projects.foundation.swipeback)
            api(libs.voyager.navigator)
            implementation(libs.kodein.compose)
            implementation(libs.voyager.transitions)
            implementation(projects.foundation.swipeback)
        }
    }
}