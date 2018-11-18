package io.pixeloutlaw.gradle.buildconfig

import java.nio.file.Path
import java.nio.file.Paths

object BuildConfigUtils {
    fun getRootOutputPath(projectBuildDir: Path): Path {
        return projectBuildDir.resolve("generated/source/buildConfig")
    }

    fun convertGroupIdToPath(groupId: Any): Path {
        val groupIdSplit = groupId.toString().split(".").toTypedArray()
        return Paths.get(".", *groupIdSplit)
    }

    fun getFileOutputPath(projectBuildDir: Path, groupId: Any): Path {
        return getRootOutputPath(projectBuildDir).resolve(convertGroupIdToPath(groupId)).resolve("BuildConfig.kt")
    }
}