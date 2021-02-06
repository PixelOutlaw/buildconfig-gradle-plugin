import de.fayard.refreshVersions.bootstrapRefreshVersions

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.9.7")
}

bootstrapRefreshVersions()

rootProject.name = "buildconfig-gradle"

include("sample-kotlin")
include("sample-groovy")

includeBuild("buildconfig-gradle-plugin")

gradle.allprojects {
    group = "io.pixeloutlaw.gradle"

    repositories {
        jcenter()
    }
}
