package io.pixeloutlaw.gradle.buildconfig

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class BuildConfigKtPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        if (target.plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
            target.logger.info("Registering generateBuildConfigKt")
            target.tasks.register("generateBuildConfigKt", GenerateBuildConfigKtTask::class.java)
            target.logger.info("Adding buildConfig generated directory to Kotlin src")
            target.extensions.findByType(KotlinJvmProjectExtension::class.java)
                ?.sourceSets
                ?.findByName("main")
                ?.kotlin
                ?.srcDir(BuildConfigUtils.getFileOutputPath(target.buildDir.toPath(), target.group))
        }
    }
}