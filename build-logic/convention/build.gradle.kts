import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.google.samples.apps.nowinandroid.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("composeMultiplatform") {
            id = "dev.srsouza.pokedex.compose"
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
        register("androidApplication") {
            id = "dev.srsouza.pokedex.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("kotlinMultiplatformLibrary") {
            id = "dev.srsouza.pokedex.multiplatform"
            implementationClass = "KotlinMultiplatformLibraryConventionPlugin"
        }
    }
}