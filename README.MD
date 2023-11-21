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


### Current stage

<img width="434" alt="Screenshot 2023-11-21 at 09 29 34" src="https://github.com/DevSrSouza/pokedex-compose-multiplatform/assets/29736164/0654b187-ea0f-4f31-97be-8445d24e8325">

<img width="436" alt="Screenshot 2023-11-21 at 09 29 47" src="https://github.com/DevSrSouza/pokedex-compose-multiplatform/assets/29736164/eccbfbef-2361-4f35-a9d5-c60762af8211">


