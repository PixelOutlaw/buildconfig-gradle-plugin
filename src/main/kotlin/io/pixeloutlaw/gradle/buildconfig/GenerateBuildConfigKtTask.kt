package io.pixeloutlaw.gradle.buildconfig

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import java.io.File

open class GenerateBuildConfigKtTask : DefaultTask() {
    private val output: File by lazy {
        project.file(
            BuildConfigUtils.getRootOutputPath(project.buildDir.toPath())
        )
    }

    init {
        description = "Generates a BuildConfig.kt with build information"
    }

    @TaskAction
    fun generateBuildConfig() {
        val buildConfigKtExtension = project.extensions.getByType(BuildConfigKtExtension::class)
        val fileSpec = buildFileSpec(buildConfigKtExtension)
        output.mkdirs()
        fileSpec.writeTo(output)
    }

    internal fun buildFileSpec(buildConfigKtExtension: BuildConfigKtExtension): FileSpec {
        val packageName = buildConfigKtExtension.packageNameOrProjectPackageName(project)
        val className = buildConfigKtExtension.className
        val buildConfigClass = ClassName(packageName, className)
        return FileSpec.builder(packageName, className)
            .addType(
                TypeSpec.objectBuilder(buildConfigClass)
                    .addProperty(
                        PropertySpec.builder("NAME", String::class, KModifier.CONST)
                            .initializer("%S", buildConfigKtExtension.appNameOrProjectName(project))
                            .build()
                    )
                    .addProperty(
                        PropertySpec.builder("VERSION", String::class, KModifier.CONST)
                            .initializer("%S", buildConfigKtExtension.versionOrProjectVersion(project))
                            .build()
                    )
                    .build()
            )
            .build()
    }
}