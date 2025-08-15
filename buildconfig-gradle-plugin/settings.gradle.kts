import de.fayard.refreshVersions.bootstrapRefreshVersions

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.60.6")
}

bootstrapRefreshVersions()

rootProject.name = "buildconfig-gradle-plugin"
