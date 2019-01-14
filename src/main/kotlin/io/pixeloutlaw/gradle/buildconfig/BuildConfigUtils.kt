package io.pixeloutlaw.gradle.buildconfig

import java.nio.file.Path
import java.nio.file.Paths

object BuildConfigUtils {
    fun getRootOutputPath(projectBuildDir: Path): Path {
        return projectBuildDir.resolve("generated/source/buildConfigKt")
    }

    fun convertGavToPackageName(groupId: Any, artifactId: Any): String {
        return "$groupId.${artifactId.toString().replace("-", "_").replace(".", "_")}"
    }

    fun convertPackageNameToPath(packageName: String): Path {
        val packageNameSplit = packageName.split(".").toTypedArray()
        return Paths.get(".", *packageNameSplit)
    }
}