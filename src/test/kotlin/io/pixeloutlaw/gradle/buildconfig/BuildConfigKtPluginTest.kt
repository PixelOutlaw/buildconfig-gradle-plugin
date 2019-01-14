package io.pixeloutlaw.gradle.buildconfig

import org.gradle.kotlin.dsl.apply
import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junitpioneer.jupiter.TempDirectory
import org.junitpioneer.jupiter.TempDirectory.TempDir
import java.nio.file.Path
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class BuildConfigKtPluginTest {
    @ExtendWith(TempDirectory::class)
    @Test
    fun doesBuildConfigPluginNotAddGenerateBuildConfigKtTaskWithoutKotlinPlugin(@TempDir tempDir: Path) {
        val project = ProjectBuilder.builder().build()
        with(project) {
            this.pluginManager.apply(BuildConfigKtPlugin::class)

            this.afterEvaluate {
                assertNull(this.tasks.findByName("generateBuildConfigKt"))
            }
        }
    }

    @ExtendWith(TempDirectory::class)
    @Test
    fun doesBuildConfigPluginAddGenerateBuildConfigKtTaskWithKotlinPlugin(@TempDir tempDir: Path) {
        val project = ProjectBuilder.builder().build()
        with(project) {
            this.pluginManager.apply(KotlinPlatformJvmPlugin::class)
            this.pluginManager.apply(BuildConfigKtPlugin::class)

            this.afterEvaluate {
                assertNotNull(this.tasks.findByName("generateBuildConfigKt"))
            }
        }
    }
}