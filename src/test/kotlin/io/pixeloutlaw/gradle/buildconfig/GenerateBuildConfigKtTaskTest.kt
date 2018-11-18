package io.pixeloutlaw.gradle.buildconfig

import assertk.assertions.isTrue
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junitpioneer.jupiter.TempDirectory
import java.io.File
import java.nio.file.Path

class GenerateBuildConfigKtTaskTest {
    @ExtendWith(TempDirectory::class)
    @Test
    fun doesGenerateBuildConfigKtTaskCreateBuildConfigKt(@TempDirectory.TempDir tempDir: Path) {
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
        GradleRunner.create()
            .withProjectDir(tempDir.toFile())
            .withArguments("generateBuildConfigKt")
            .withPluginClasspath()
            .build()
        val buildConfigFile =
            File(tempDir.toFile(), "build/generated/source/buildConfig/io/pixeloutlaw/gradle/BuildConfig.kt")
        assertk.assert(buildConfigFile.exists()).isTrue()
    }
}