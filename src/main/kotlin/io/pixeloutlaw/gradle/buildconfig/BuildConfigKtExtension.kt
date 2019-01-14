package io.pixeloutlaw.gradle.buildconfig

/**
 * Configuration for the `generateBuildConfigKt` task. When created by the plugin,
 * the `appName`, `version`, and `packageName` properties are automatically determined
 * by the data available in the project itself.
 */
open class BuildConfigKtExtension @JvmOverloads constructor(
    var appName: String = "",
    var version: String = "",
    var packageName: String = "",
    var className: String = "BuildConfig"
)
