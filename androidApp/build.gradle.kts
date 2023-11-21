plugins {
    id("dev.srsouza.pokedex.android.application")
}

dependencies {
    implementation(projects.composeApp)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activityCompose)
}

android {
    defaultConfig {
        applicationId = "dev.srsouza.pokedex.androidApp"
    }
}