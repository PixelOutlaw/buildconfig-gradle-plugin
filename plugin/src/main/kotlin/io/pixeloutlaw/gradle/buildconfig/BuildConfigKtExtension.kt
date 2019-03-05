package io.pixeloutlaw.gradle.buildconfig

import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultAppName
import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultClassName
import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultPackageName
import io.pixeloutlaw.gradle.buildconfig.BuildConfigKtExtension.Companion.defaultVersion

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
     * @property defaultAppName default app name, if [appName] equals this, the task will determine the value
     * @property defaultVersion default version, if [version] equals this, the task will determine the value
     * @property defaultPackageName default package name, if [packageName] equals this, the task will determine the value
     * @property defaultClassName default class name
     */
    companion object {
        const val defaultAppName = ""
        const val defaultVersion = ""
        const val defaultPackageName = ""
        const val defaultClassName = "BuildConfig"
    }

    var appName: String = defaultAppName
    var version: String = defaultVersion
    var packageName: String = defaultPackageName
    var className: String = defaultClassName
}
