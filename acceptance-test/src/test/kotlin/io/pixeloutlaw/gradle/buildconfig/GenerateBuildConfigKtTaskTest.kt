package io.pixeloutlaw.gradle.buildconfig

import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junitpioneer.jupiter.TempDirectory
import org.junitpioneer.jupiter.TempDirectory.TempDir
import java.io.File
import java.nio.file.Path
import kotlin.test.assertTrue

class GenerateBuildConfigKtTaskTest {
    private val gradleVersion: String = System.getProperty("current.gradle.version")

    @ExtendWith(TempDirectory::class)
    @Test
    fun doesGenerateBuildConfigKtTaskCreateBuildConfigKt(@TempDir tempDir: Path) {
        setupBuildGradle(tempDir)
        setupSettingsGradle(tempDir, "test")
        GradleRunner.create()
            .withGradleVersion(gradleVersion)
            .withProjectDir(tempDir.toFile())
            .withArguments("generateBuildConfigKt")
            .withPluginClasspath()
            .build()
        val buildConfigFile =
            File(tempDir.toFile(), "build/generated/source/buildConfigKt/io/pixeloutlaw/gradle/test/BuildConfig.kt")
        assertTrue(buildConfigFile.exists())
    }

    @ExtendWith(TempDirectory::class)
    @Test
    fun doesGenerateBuildConfigKtTaskCreateBuildConfigKtForDotName(@TempDir tempDir: Path) {
        setupBuildGradle(tempDir)
        setupSettingsGradle(tempDir, "test.test")
        GradleRunner.create()
            .withGradleVersion(gradleVersion)
            .withProjectDir(tempDir.toFile())
            .withArguments("generateBuildConfigKt")
            .withPluginClasspath()
            .build()
        val buildConfigFile =
            File(tempDir.toFile(), "build/generated/source/buildConfigKt/io/pixeloutlaw/gradle/test_test/BuildConfig.kt")
        assertTrue(buildConfigFile.exists())
    }

    @ExtendWith(TempDirectory::class)
    @Test
    fun doesGenerateBuildConfigKtTaskCreateBuildConfigKtForHyphen(@TempDir tempDir: Path) {
        setupBuildGradle(tempDir)
        setupSettingsGradle(tempDir, "test-test")
        GradleRunner.create()
            .withGradleVersion(gradleVersion)
            .withProjectDir(tempDir.toFile())
            .withArguments("generateBuildConfigKt")
            .withPluginClasspath()
            .build()
        val buildConfigFile =
            File(tempDir.toFile(), "build/generated/source/buildConfigKt/io/pixeloutlaw/gradle/test_test/BuildConfig.kt")
        assertTrue(buildConfigFile.exists())
    }

    private fun setupBuildGradle(tempDir: Path) {
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
    }

    private fun setupSettingsGradle(tempDir: Path, name: String) {
        File(tempDir.toFile(), "settings.gradle.kts").run {
            writeText(
                """
                    rootProject.name = "$name"
                """.trimIndent()
            )
        }
    }
}