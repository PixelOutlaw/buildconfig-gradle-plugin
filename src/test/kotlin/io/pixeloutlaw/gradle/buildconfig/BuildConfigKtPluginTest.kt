package io.pixeloutlaw.gradle.buildconfig

import assertk.assertions.contains
import assertk.assertions.doesNotContain
import assertk.assertions.isNotNull
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junitpioneer.jupiter.TempDirectory
import org.junitpioneer.jupiter.TempDirectory.TempDir
import java.io.File
import java.nio.file.Path

class BuildConfigKtPluginTest {
    @ExtendWith(TempDirectory::class)
    @Test
    fun doesBuildConfigPluginNotAddGenerateBuildConfigKtTaskWithoutKotlinPlugin(@TempDir tempDir: Path) {
        File(tempDir.toFile(), "build.gradle").run {
            writeText(
                """
                plugins {
                    id("io.pixeloutlaw.gradle.buildconfigkt")
                }

                group = "io.pixeloutlaw.gradle"
                version = "420.0.0-SNAPSHOT"
            """.trimIndent()
            )
        }
        val buildResult = GradleRunner.create()
            .withProjectDir(tempDir.toFile())
            .withArguments("tasks", "--all")
            .withPluginClasspath()
            .build()
        assertk.assert(buildResult.output).isNotNull {
            it.doesNotContain("generateBuildConfigKt")
        }
    }

    @ExtendWith(TempDirectory::class)
    @Test
    fun doesBuildConfigPluginAddGenerateBuildConfigKtTaskWithKotlinPlugin(@TempDir tempDir: Path) {
        File(tempDir.toFile(), "build.gradle").run {
            writeText(
                """
                plugins {
                    id("io.pixeloutlaw.gradle.buildconfigkt")
                    id "org.jetbrains.kotlin.jvm" version "1.3.10"
                }

                group = "io.pixeloutlaw.gradle"
                version = "420.0.0-SNAPSHOT"
            """.trimIndent()
            )
        }
        val buildResult = GradleRunner.create()
            .withProjectDir(tempDir.toFile())
            .withArguments("tasks", "--all")
            .withPluginClasspath()
            .build()
        assertk.assert(buildResult.output).isNotNull {
            it.contains("generateBuildConfigKt")
        }
    }
}