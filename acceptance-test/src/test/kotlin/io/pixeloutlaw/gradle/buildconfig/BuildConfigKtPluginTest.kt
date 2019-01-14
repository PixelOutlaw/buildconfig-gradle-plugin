package io.pixeloutlaw.gradle.buildconfig

import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junitpioneer.jupiter.TempDirectory
import org.junitpioneer.jupiter.TempDirectory.TempDir
import java.io.File
import java.nio.file.Path
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BuildConfigKtPluginTest {
    val gradleVersion: String = System.getProperty("current.gradle.version")

    @ExtendWith(TempDirectory::class)
    @Test
    fun doesBuildConfigPluginNotAddGenerateBuildConfigKtTaskWithoutKotlinPlugin(@TempDir tempDir: Path) {
        File(tempDir.toFile(), "build.gradle.kts").run {
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
            .withGradleVersion(gradleVersion)
            .withProjectDir(tempDir.toFile())
            .withArguments("tasks", "--all")
            .withPluginClasspath()
            .build()
        assertNotNull(buildResult.output) {
            assertFalse(it.contains("generateBuildConfigKt"))
        }
    }

    @ExtendWith(TempDirectory::class)
    @Test
    fun doesBuildConfigPluginAddGenerateBuildConfigKtTaskWithKotlinPluginDefinedBefore(@TempDir tempDir: Path) {
        File(tempDir.toFile(), "build.gradle.kts").run {
            writeText(
                """
                plugins {
                    id("org.jetbrains.kotlin.jvm") version "1.3.11"
                    id("io.pixeloutlaw.gradle.buildconfigkt")
                }

                group = "io.pixeloutlaw.gradle"
                version = "420.0.0-SNAPSHOT"
            """.trimIndent()
            )
        }
        val buildResult = GradleRunner.create()
            .withGradleVersion(gradleVersion)
            .withProjectDir(tempDir.toFile())
            .withArguments("tasks", "--all")
            .withPluginClasspath()
            .build()
        assertNotNull(buildResult.output) {
            assertTrue(it.contains("generateBuildConfigKt"))
        }
    }

    @ExtendWith(TempDirectory::class)
    @Test
    fun doesBuildConfigPluginAddGenerateBuildConfigKtTaskWithKotlinPluginDefinedAfter(@TempDir tempDir: Path) {
        File(tempDir.toFile(), "build.gradle.kts").run {
            writeText(
                """
                plugins {
                    id("io.pixeloutlaw.gradle.buildconfigkt")
                    id("org.jetbrains.kotlin.jvm") version "1.3.11"
                }

                group = "io.pixeloutlaw.gradle"
                version = "420.0.0-SNAPSHOT"
            """.trimIndent()
            )
        }
        val buildResult = GradleRunner.create()
            .withGradleVersion(gradleVersion)
            .withProjectDir(tempDir.toFile())
            .withArguments("tasks", "--all")
            .withPluginClasspath()
            .build()
        assertNotNull(buildResult.output) {
            assertTrue(it.contains("generateBuildConfigKt"))
        }
    }
}