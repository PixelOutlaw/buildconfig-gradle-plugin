package io.pixeloutlaw.gradle.buildconfig

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
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

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

        assertEquals("io.pixeloutlaw.testGenerateBuildConfigKt", fileSpec.packageName)
        assertEquals("BuildConfig", fileSpec.name)
    }

    @Test
    fun doesBuildFileSpecUseConfiguredPackageNameForConfiguredExtension() {
        buildConfigKtExtension.packageName = "io.dankmemes"
        val fileSpec = generateBuildConfigKtTask.buildFileSpec(buildConfigKtExtension)

        assertEquals("io.dankmemes", fileSpec.packageName)
        assertEquals("BuildConfig", fileSpec.name)
    }

    @Test
    fun doesBuildFileSpecUseProjectNameForDefaultExtension() {
        val fileSpec = generateBuildConfigKtTask.buildFileSpec(buildConfigKtExtension)
        val typeSpecs = fileSpec.members.filter { it is TypeSpec && it.name == buildConfigKtExtension.className }.map { it as TypeSpec }
        assertEquals(1, typeSpecs.size)
        val buildConfigTypeSpec = typeSpecs.first()
        assertEquals(2, buildConfigTypeSpec.propertySpecs.size)
        val namePropertySpec = buildConfigTypeSpec.propertySpecs.first { it.name == "NAME" }
        assertNotNull(namePropertySpec)
        assertEquals("\"testGenerateBuildConfigKt\"", namePropertySpec.initializer?.toString())
    }

    @Test
    fun doesBuildFileSpecUseProjectVersionForDefaultExtension() {
        val fileSpec = generateBuildConfigKtTask.buildFileSpec(buildConfigKtExtension)
        val typeSpecs = fileSpec.members.filter { it is TypeSpec && it.name == buildConfigKtExtension.className }.map { it as TypeSpec }
        assertEquals(1, typeSpecs.size)
        val buildConfigTypeSpec = typeSpecs.first()
        assertEquals(2, buildConfigTypeSpec.propertySpecs.size)
        val versionPropertySpec = buildConfigTypeSpec.propertySpecs.first { it.name == "VERSION" }
        assertNotNull(versionPropertySpec)
        assertEquals("\"4.2.0\"", versionPropertySpec.initializer?.toString())
    }
}