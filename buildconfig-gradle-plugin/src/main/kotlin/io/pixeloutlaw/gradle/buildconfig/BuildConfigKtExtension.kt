package io.pixeloutlaw.gradle.buildconfig

import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultAppName
import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultClassName
import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultPackageName
import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultVersion
import org.gradle.api.Project

/**
 * Configuration for the `generateBuildConfigKt` task.
 *
 * @property appName name of the project, defaults to [defaultAppName]
 * @property version version of the project, defaults to [defaultVersion]
 * @property packageName package name for generated build config file, defaults to [defaultPackageName]
 * @property className class name for generated build config file, defaults to [defaultClassName]
 */
open class BuildConfigKtExtension {
    /**
     * Contains default values for the `generateBuildConfigKt` task.
     * @property defaultAppName default app name, if [appName] equals this, the task will determine it
     * @property defaultGroup default group, if [group] equals this, the task will determine it
     * @property defaultVersion default version, if [version] equals this, the task will determine it
     * @property defaultPackageName default package name, if [packageName] equals this, the task will determine it
     * @property defaultClassName default class name
     */
    companion object {
        const val defaultAppName = ""
        const val defaultGroup = ""
        const val defaultVersion = ""
        const val defaultPackageName = ""
        const val defaultClassName = "BuildConfig"
    }

    var appName: String = defaultAppName
    var group: String = defaultGroup
    var version: String = defaultVersion
    var packageName: String = defaultPackageName
    var className: String = defaultClassName

    /**
     * If [BuildConfigKtExtension.appName] is equal to [defaultAppName], returns `project.name`. Otherwise,
     * returns [appName].
     *
     * @return appName for use in `generateBuildConfigKt`
     */
    fun appNameOrProjectName(project: Project): String {
        return if (appName == defaultAppName) {
            project.name
        } else {
            appName
        }
    }

    /**
     * If [BuildConfigKtExtension.group] is equal to [defaultGroup], returns `project.group`. Otherwise,
     * returns [group].
     *
     * @return group for use in `generateBuildConfigKt`
     */
    fun groupOrProjectGroup(project: Project): String {
        return if (group == defaultGroup) {
            project.group.toString()
        } else {
            group
        }
    }

    /**
     * If [BuildConfigKtExtension.version] is equal to [defaultVersion], returns `project.version`. Otherwise,
     * returns [version].
     *
     * @return version for use in `generateBuildConfigKt`
     */
    fun versionOrProjectVersion(project: Project): String {
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
    fun packageNameOrProjectPackageName(project: Project): String {
        return if (packageName == defaultPackageName) {
            BuildConfigUtils.convertGavToPackageName(project.group, project.name)
        } else {
            packageName
        }
    }
}
