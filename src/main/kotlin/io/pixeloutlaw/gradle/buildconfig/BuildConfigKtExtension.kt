package io.pixeloutlaw.gradle.buildconfig

import org.gradle.api.Project

/**
 * Configuration for the `generateBuildConfigKt` task. When created by the plugin,
 * the `appName`, `version`, and `packageName` properties are automatically determined
 * by the data available in the project itself.
 */
open class BuildConfigKtExtension (
    project: Project
) {
    var appName: String = ""
    var version: String = ""
    var packageName: String = ""
    var className: String = "BuildConfig"

    init {
        appName = project.name
        version = project.version.toString()
        packageName = BuildConfigUtils.convertGavToPackageName(project.group, project.name)
    }
}
