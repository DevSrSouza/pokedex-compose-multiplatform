enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Pokedex"
include(
    ":composeApp",
    ":androidApp",
    ":iosUmbrella",

    ":foundation:kodein",
    ":foundation:core",
    ":foundation:serialization",
    ":foundation:network",
    ":foundation:theme",
    ":foundation:components",
    ":foundation:navigation",
    ":foundation:step-view-model",
    ":foundation:error",
    ":foundation:swipeback",
)

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
