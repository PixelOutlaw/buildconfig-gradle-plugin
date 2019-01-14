package io.pixeloutlaw.gradle.buildconfig

import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junitpioneer.jupiter.TempDirectory
import java.io.File
import java.nio.file.Path
import kotlin.test.assertTrue

class GenerateBuildConfigKtTaskTest {
    @ExtendWith(TempDirectory::class)
    @Test
    fun doesGenerateBuildConfigKtTaskCreateBuildConfigKt(@TempDirectory.TempDir tempDir: Path) {
        setupBuildGradle(tempDir)
        setupSettingsGradle(tempDir, "test")
        GradleRunner.create()
            .withProjectDir(tempDir.toFile())
            .withArguments("generateBuildConfigKt")
            .withPluginClasspath()
            .build()
        val buildConfigFile =
            File(tempDir.toFile(), "build/generated/source/buildConfig/io/pixeloutlaw/gradle/test/BuildConfig.kt")
        assertTrue(buildConfigFile.exists())
    }

    @ExtendWith(TempDirectory::class)
    @Test
    fun doesGenerateBuildConfigKtTaskCreateBuildConfigKtForDotName(@TempDirectory.TempDir tempDir: Path) {
        setupBuildGradle(tempDir)
        setupSettingsGradle(tempDir, "test.test")
        GradleRunner.create()
            .withProjectDir(tempDir.toFile())
            .withArguments("generateBuildConfigKt")
            .withPluginClasspath()
            .build()
        val buildConfigFile =
            File(tempDir.toFile(), "build/generated/source/buildConfig/io/pixeloutlaw/gradle/test_test/BuildConfig.kt")
        assertTrue(buildConfigFile.exists())
    }

    @ExtendWith(TempDirectory::class)
    @Test
    fun doesGenerateBuildConfigKtTaskCreateBuildConfigKtForHyphen(@TempDirectory.TempDir tempDir: Path) {
        setupBuildGradle(tempDir)
        setupSettingsGradle(tempDir, "test-test")
        GradleRunner.create()
            .withProjectDir(tempDir.toFile())
            .withArguments("generateBuildConfigKt")
            .withPluginClasspath()
            .build()
        val buildConfigFile =
            File(tempDir.toFile(), "build/generated/source/buildConfig/io/pixeloutlaw/gradle/test_test/BuildConfig.kt")
        assertTrue(buildConfigFile.exists())
    }

    private fun setupBuildGradle(tempDir: Path) {
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
    }

    private fun setupSettingsGradle(tempDir: Path, name: String) {
        File(tempDir.toFile(), "settings.gradle").run {
            writeText(
                """
                    rootProject.name = "$name"
                """.trimIndent()
            )
        }
    }
}