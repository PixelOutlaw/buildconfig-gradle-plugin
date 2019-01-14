package io.pixeloutlaw.gradle.buildconfig

import org.gradle.kotlin.dsl.apply
import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class BuildConfigKtPluginTest {
    @Test
    fun doesBuildConfigPluginNotAddGenerateBuildConfigKtTaskWithoutKotlinPlugin() {
        val project = ProjectBuilder.builder().build()
        with(project) {
            this.pluginManager.apply(BuildConfigKtPlugin::class)

            this.afterEvaluate {
                assertNull(this.tasks.findByName("generateBuildConfigKt"))
            }
        }
    }

    @Test
    fun doesBuildConfigPluginAddGenerateBuildConfigKtTaskWithKotlinPlugin() {
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