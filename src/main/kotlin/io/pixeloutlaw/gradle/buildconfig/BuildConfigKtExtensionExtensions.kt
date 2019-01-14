package io.pixeloutlaw.gradle.buildconfig

import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultAppName
import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultPackageName
import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultVersion
import org.gradle.api.Project

/**
 * If [BuildConfigKtExtension.appName] is equal to [defaultAppName], returns `project.name`. Otherwise, returns [appName].
 *
 * @return appName for use in `generateBuildConfigKt`
 */
fun BuildConfigKtExtension.appNameOrProjectName(project: Project): String {
    return if (appName == defaultAppName) {
        project.name
    } else {
        appName
    }
}

/**
 * If [BuildConfigKtExtension.version] is equal to [defaultVersion], returns `project.name`. Otherwise, returns [version].
 *
 * @return version for use in `generateBuildConfigKt`
 */
fun BuildConfigKtExtension.versionOrProjectVersion(project: Project): String {
    return if (version == defaultVersion) {
        project.version.toString()
    } else {
        version
    }
}

/**
 * If [BuildConfigKtExtension.packageName] is equal to [defaultPackageName], returns
 * `BuildConfigUtils.convertGavToPackageName(project.group, project.name)`. Otherwise, returns [appName].
 *
 * @return packageName for use in `generateBuildConfigKt`
 */
fun BuildConfigKtExtension.packageNameOrProjectPackageName(project: Project): String {
    return if (packageName == defaultPackageName) {
        BuildConfigUtils.convertGavToPackageName(project.group, project.name)
    } else {
        packageName
    }
}