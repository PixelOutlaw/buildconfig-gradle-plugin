package io.pixeloutlaw.gradle.buildconfig

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class BuildConfigKtPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // afterEvaluate required to get access to plugin internals
        target.afterEvaluate {
            if (it.plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
                it.logger.info("Registering generateBuildConfigKt")
                it.tasks.register("generateBuildConfigKt", GenerateBuildConfigKtTask::class.java)
                it.logger.info("Adding buildConfig generated directory to Kotlin src")
                it.extensions.findByType(KotlinJvmProjectExtension::class.java)
                    ?.sourceSets
                    ?.findByName("main")
                    ?.kotlin
                    ?.srcDir(BuildConfigUtils.getFileOutputPath(it.buildDir.toPath(), it.group))
            }
        }
    }
}