package io.pixeloutlaw.gradle.buildconfig

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

open class GenerateBuildConfigKtTask : DefaultTask() {
    @OutputFile
    val output: File = project.file(
        BuildConfigUtils.getFileOutputPath(project.buildDir.toPath(), project.group)
    )

    init {
        description = "Generates a BuildConfig.kt with build information"
    }

    @TaskAction
    fun generateBuildConfig() {
        val buildConfigClass = ClassName(project.group.toString(), "BuildConfig")
        val file = FileSpec.builder(project.group.toString(), "BuildConfig")
            .addType(
                TypeSpec.objectBuilder(buildConfigClass)
                    .addProperty(
                        PropertySpec.builder("NAME", String::class, KModifier.CONST)
                            .initializer("%S", project.name)
                            .build()
                    )
                    .addProperty(
                        PropertySpec.builder("VERSION", String::class, KModifier.CONST)
                            .initializer("%S", project.version.toString())
                            .build()
                    )
                    .build()
            )
            .build()
        output.mkdirs()
        val fileOutput = StringBuilder()
        file.writeTo(fileOutput)
        file.writeTo(output)
        logger.debug("Wrote \"{}\" to {}", fileOutput, output.absolutePath)
    }
}