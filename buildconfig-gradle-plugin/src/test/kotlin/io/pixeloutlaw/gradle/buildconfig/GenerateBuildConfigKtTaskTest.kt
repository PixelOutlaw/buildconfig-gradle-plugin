package io.pixeloutlaw.gradle.buildconfig

import com.google.common.truth.Truth.assertThat
import com.squareup.kotlinpoet.TypeSpec
import org.gradle.api.Project
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByName
import org.gradle.kotlin.dsl.getByType
import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GenerateBuildConfigKtTaskTest {
    private lateinit var project: Project
    private lateinit var generateBuildConfigKtTask: GenerateBuildConfigKtTask
    private lateinit var buildConfigKtExtension: BuildConfigKtExtension

    @BeforeEach
    fun setup() {
        project = ProjectBuilder.builder().withName("testGenerateBuildConfigKt").build()
        project.group = "io.pixeloutlaw"
        project.version = "4.2.0"
        project.pluginManager.apply(KotlinPlatformJvmPlugin::class)
        project.pluginManager.apply(BuildConfigKtPlugin::class)
        (project as ProjectInternal).evaluate() // force the project to evaluate so we can get tasks
        generateBuildConfigKtTask = project.tasks.getByName("generateBuildConfigKt", GenerateBuildConfigKtTask::class)
        buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)
    }

    @Test
    fun doesBuildFileSpecUseProjectPackageNameForDefaultExtension() {
        val fileSpec = generateBuildConfigKtTask.buildFileSpec(buildConfigKtExtension)

        assertThat("io.pixeloutlaw.testGenerateBuildConfigKt").isEqualTo(fileSpec.packageName)
        assertThat("BuildConfig").isEqualTo(fileSpec.name)
    }

    @Test
    fun doesBuildFileSpecUseConfiguredPackageNameForConfiguredExtension() {
        buildConfigKtExtension.packageName = "io.dankmemes"
        val fileSpec = generateBuildConfigKtTask.buildFileSpec(buildConfigKtExtension)

        assertThat("io.dankmemes").isEqualTo(fileSpec.packageName)
        assertThat("BuildConfig").isEqualTo(fileSpec.name)
    }

    @Test
    fun doesBuildFileSpecUseProjectNameForDefaultExtension() {
        val fileSpec = generateBuildConfigKtTask.buildFileSpec(buildConfigKtExtension)
        val typeSpecs = fileSpec.members.filter { it is TypeSpec && it.name == buildConfigKtExtension.className }
            .map { it as TypeSpec }
        assertThat(typeSpecs.size).isEqualTo(1)
        val buildConfigTypeSpec = typeSpecs.first()
        assertThat(buildConfigTypeSpec.propertySpecs.size).isEqualTo(3)
        val namePropertySpec = buildConfigTypeSpec.propertySpecs.first { it.name == "NAME" }
        assertThat(namePropertySpec).isNotNull()
        assertThat("\"testGenerateBuildConfigKt\"").isEqualTo(namePropertySpec.initializer?.toString())
    }

    @Test
    fun doesBuildFileSpecUseProjectGroupForDefaultExtension() {
        val fileSpec = generateBuildConfigKtTask.buildFileSpec(buildConfigKtExtension)
        val typeSpecs = fileSpec.members.filter { it is TypeSpec && it.name == buildConfigKtExtension.className }
            .map { it as TypeSpec }
        assertThat(typeSpecs.size).isEqualTo(1)
        val buildConfigTypeSpec = typeSpecs.first()
        assertThat(buildConfigTypeSpec.propertySpecs.size).isEqualTo(3)
        val groupPropertySpec = buildConfigTypeSpec.propertySpecs.first { it.name == "GROUP" }
        assertThat(groupPropertySpec).isNotNull()
        assertThat("\"io.pixeloutlaw\"").isEqualTo(groupPropertySpec.initializer?.toString())
    }

    @Test
    fun doesBuildFileSpecUseProjectVersionForDefaultExtension() {
        val fileSpec = generateBuildConfigKtTask.buildFileSpec(buildConfigKtExtension)
        val typeSpecs = fileSpec.members.filter { it is TypeSpec && it.name == buildConfigKtExtension.className }
            .map { it as TypeSpec }
        assertThat(typeSpecs.size).isEqualTo(1)
        val buildConfigTypeSpec = typeSpecs.first()
        assertThat(buildConfigTypeSpec.propertySpecs.size).isEqualTo(3)
        val versionPropertySpec = buildConfigTypeSpec.propertySpecs.first { it.name == "VERSION" }
        assertThat(versionPropertySpec).isNotNull()
        assertThat("\"4.2.0\"").isEqualTo(versionPropertySpec.initializer?.toString())
    }
}
