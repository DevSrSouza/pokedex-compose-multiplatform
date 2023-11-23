# Pokedex

Compose Multiplatform Pokedex app. [WIP]

## Before running!
 - install JDK 17 on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :androidApp:assembleDebug`
 - find `.apk` file in `androidApp/build/outputs/apk/debug/androidApp-debug.apk`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

### Feature Architecture

```
data/
  ktorfit api
  response models
  repository
domain/
  domain models
  mappers
presentation/
  steps
  components
  strings
```

### Libraries

- UI: [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform)
- Navigation: [Voyager](https://github.com/adrielcafe/voyager/)
- Strings I18N/L10N: [Lyricist](https://github.com/adrielcafe/lyricist/)
- Icons: [Compose Icons](https://github.com/DevSrSouza/compose-icons)
- Network: [Ktor](https://github.com/ktorio/ktor) + [Ktorfit](https://github.com/Foso/Ktorfit)
- Dependency Injection: [Kodein](https://github.com/kosi-libs/Kodein) + Custom Voyager Scopes
- Image Loading: [Compose ImageLoader](https://github.com/qdsfdhvh/compose-imageloader) ([Coil is not yet Multiplatform](https://github.com/coil-kt/coil/issues/842))
- Json parsing: [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
- ViewModel + State: [Custom Implementation](https://github.com/DevSrSouza/pokedex-compose-multiplatform/tree/main/foundation/step-view-model/src/commonMain/kotlin/dev.srsouza.pokedex.foundation.stepviewmodel)

### Modules explanations

- Foundation Core: Holds custom pure Kotlin functions, currently, the place of the `safeCatching` extension for avoiding Coroutine Scopes not being able to cancel when code called from `runCatching`.
- Foundation Theme: The Compose Material 3 Theming + Platform System theme lookup
- Foundation Swipe Back: Custom component for support Navigation Swipe Back in iOS
- Foundation Serialization: Singleton with default Kotlinx.serialization configuration
- Foundation Network: Setup of Ktor HttpClient and Ktorfit build function
- Foundation Components: Commum Compose Components like, HeaderBar, Error State View, Layout and LazyList Pagination utils
- Foundation Error: The ErrorState model and error mapping
- Foundation Kodein: Custom Kodein Scopes for Voyager Navigator and Screen lifecycle, this make possible to have a dependency that survives until the Navigator or the Screen survives.
- Foundation Step ViewModel: A Custom ViewModel implementation that is opinionated that holds the UI State and the Business State, similar to [BloC Cubit](https://bloclibrary.dev/#/coreconcepts?id=cubit)
- Foundation Navigation: It has multiple responsibilities, Setup Voyager Navigator with the Kodein Graph + Context, Defines and Provide the Scaffold Composition Local API and most important, defines the Step and StepStateful.
- ---
- composeApp: The Compose Main Application Module, it where is has the Main Composable
- androidApp: The Android App with the Activity that initialize the Main Composable from `composeApp`
- iosUmbrella: The module that generates the Framework and exposes the Main Composable from `composeApp` as a UiViewController
- iosApp: A iOS App using Swift UI that has a build phase for building de `iosUmbrella` module and initializing the UI by consuming the UiViewController from the `iosUmbrella` module.
- ---
- Gradle: Convention plugins, Kotlin Scripting, Version Catalog and Typesafe Project Accessor


### Current state of the project

<img width="434" alt="Screenshot 2023-11-21 at 09 29 34" src="https://github.com/DevSrSouza/pokedex-compose-multiplatform/assets/29736164/0654b187-ea0f-4f31-97be-8445d24e8325">

<img width="437" alt="image" src="https://github.com/DevSrSouza/pokedex-compose-multiplatform/assets/29736164/52c3517e-a040-46f6-ac81-652a96cc4060">

<img width="435" alt="image" src="https://github.com/DevSrSouza/pokedex-compose-multiplatform/assets/29736164/e18f8653-90c2-4457-86cb-b3605accd364">


