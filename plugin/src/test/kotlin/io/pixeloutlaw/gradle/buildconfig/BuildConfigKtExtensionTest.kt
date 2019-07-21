package io.pixeloutlaw.gradle.buildconfig

import com.google.common.truth.Truth.assertThat
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.junit.jupiter.api.Test

class BuildConfigKtExtensionTest {
    @Test
    fun doesAppNameOrProjectNameReturnProjectNameForNoProjectConfigurationOrExtensionConfiguration() {
        val project = constructProject()
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)

        assertThat("test").isEqualTo(buildConfigKtExtension.appNameOrProjectName(project))
    }

    @Test
    fun doesAppNameOrProjectNameReturnProjectNameForNoExtensionConfiguration() {
        val project = constructProject(group = "io.pixeloutlaw", name = "buildConfigKt", version = "4.2.0")
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)

        assertThat("buildConfigKt").isEqualTo(buildConfigKtExtension.appNameOrProjectName(project))
    }

    @Test
    fun doesAppNameOrProjectNameReturnAppNameForExtensionConfiguration() {
        val project = constructProject(group = "io.pixeloutlaw", name = "buildConfigKt", version = "4.2.0")
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)
        buildConfigKtExtension.appName = "dankMemes"

        assertThat("dankMemes").isEqualTo(buildConfigKtExtension.appNameOrProjectName(project))
    }

    @Test
    fun `does groupOrProjectGroup return project group for no project configuration or extension configuration`() {
        val project = constructProject()
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)

        assertThat("").isEqualTo(buildConfigKtExtension.groupOrProjectGroup(project))
    }

    @Test
    fun `does groupOrProjectGroup return project group for no extension configuration`() {
        val project = constructProject(group = "io.pixeloutlaw", name = "buildConfigKt", version = "4.2.0")
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)

        assertThat("io.pixeloutlaw").isEqualTo(buildConfigKtExtension.groupOrProjectGroup(project))
    }

    @Test
    fun `does groupOrProjectGroup return group for extension configuration`() {
        val project = constructProject(group = "io.pixeloutlaw", name = "buildConfigKt", version = "4.2.0")
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)
        buildConfigKtExtension.group = "dankMemes"

        assertThat("dankMemes").isEqualTo(buildConfigKtExtension.groupOrProjectGroup(project))
    }

    @Test
    fun doesVersionOrProjectVersionReturnProjectVersionForNoProjectConfigurationOrExtensionConfiguration() {
        val project = constructProject()
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)

        assertThat("unspecified").isEqualTo(buildConfigKtExtension.versionOrProjectVersion(project))
    }

    @Test
    fun doesVersionOrProjectVersionReturnProjectVersionForNoExtensionConfiguration() {
        val project = constructProject(group = "io.pixeloutlaw", name = "buildConfigKt", version = "4.2.0")
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)

        assertThat("4.2.0").isEqualTo(buildConfigKtExtension.versionOrProjectVersion(project))
    }

    @Test
    fun doesVersionOrProjectVersionReturnVersionForExtensionConfiguration() {
        val project = constructProject(group = "io.pixeloutlaw", name = "buildConfigKt", version = "4.2.0")
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)
        buildConfigKtExtension.version = "4.2.1"

        assertThat("4.2.1").isEqualTo(buildConfigKtExtension.versionOrProjectVersion(project))
    }

    @Test
    fun doesPackageNameOrProjectPackageNameReturnProjectPackageNameForNoProjectConfigurationOrExtensionConfiguration() {
        val project = constructProject()
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)

        assertThat("test").isEqualTo(buildConfigKtExtension.packageNameOrProjectPackageName(project))
    }

    @Test
    fun doesPackageNameOrProjectPackageNameReturnProjectPackageNameForNoExtensionConfiguration() {
        val project = constructProject(group = "io.pixeloutlaw", name = "buildConfigKt", version = "4.2.0")
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)

        assertThat("io.pixeloutlaw.buildConfigKt").isEqualTo(
            buildConfigKtExtension.packageNameOrProjectPackageName(
                project
            )
        )
    }

    @Test
    fun doesPackageNameOrProjectPackageNameReturnPackageNameForExtensionConfiguration() {
        val project = constructProject(group = "io.pixeloutlaw", name = "buildConfigKt", version = "4.2.0")
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)
        buildConfigKtExtension.packageName = "io.pixeloutlaw.dankMemes"

        assertThat("io.pixeloutlaw.dankMemes").isEqualTo(buildConfigKtExtension.packageNameOrProjectPackageName(project))
    }

    private fun constructProject(group: String? = null, name: String = "test", version: String? = null): Project {
        val project = ProjectBuilder.builder().withName(name).build()
        if (group != null) {
            project.group = group
        }
        if (version != null) {
            project.version = version
        }
        project.pluginManager.apply(KotlinPlatformJvmPlugin::class)
        project.pluginManager.apply(BuildConfigKtPlugin::class)
        return project
    }
}
