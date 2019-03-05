# buildConfigKt
[![][gradlePluginPortal]](https://plugins.gradle.org/plugin/io.pixeloutlaw.gradle.buildconfigkt)
[![][travisCi]](https://travis-ci.com/PixelOutlaw/buildconfig-gradle-plugin)

## Requirements
* Gradle >= 4.9
* Kotlin and JVM projects

## Installation
Edit the build.gradle(.kts) file of the project where you want to add a `BuildConfig.kt`:
```kotlin
plugins {
    id("io.pixeloutlaw.gradle.buildconfigkt") version "1.0.0"
}
```

## Usage
The plugin will automatically configure itself based on your project to generate a `BuildConfig.kt`. This can be
configured using the `buildConfigKt` extension.

### Simple Example
Using automatic configuration, below is a sample output from this plugin.
```kotlin
// build.gradle.kts
plugins {
    kotlin("jvm")
    id("io.pixeloutlaw.gradle.buildconfigkt") version "1.0.0"
}

project.group = "simple.example"
project.version = "0.0.1"
```
```kotlin
// settings.gradle.kts
rootProject.name = "example"
```
```kotlin
// build/generated/source/buildConfigKt/simple/example/BuildConfig.kt
package simple.example

import kotlin.String

object BuildConfig {
    const val NAME: String = "example"

    const val VERSION: String = "0.0.1"
}
```
### Full Example
Using all of the fields available in the extension, here is the output from this plugin.
```kotlin
// build.gradle.kts
plugins {
    kotlin("jvm")
    id("io.pixeloutlaw.gradle.buildconfigkt") version "1.0.0"
}

project.group = "simple.example"
project.version = "0.0.1"

buildConfigKt {
    appName = "StarWars"
    packageName = "galaxy.far.far.away"
    version = "2.0.19"
    className = "Kenobi"
}
```
```kotlin
// settings.gradle.kts
rootProject.name = "example"
```
```kotlin
// build/generated/source/buildConfigKt/galaxy/far/far/away/Kenobi.kt
package galaxy.far.far.away

import kotlin.String

object Kenobi {
    const val NAME: String = "StarWars"

    const val VERSION: String = "2.0.19"
}
```

## Development
Open the `composite` project in IntelliJ in order to be able to edit and test the plugin.

[gradlePluginPortal]: https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/gradle/plugin/io/pixeloutlaw/gradle/buildconfigkt/io.pixeloutlaw.gradle.buildconfigkt.gradle.plugin/maven-metadata.xml.svg "Gradle Plugin Portal"
[travisCi]: https://img.shields.io/travis/com/PixelOutlaw/buildconfig-gradle-plugin.svg