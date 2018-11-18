package io.pixeloutlaw.gradle.buildconfig

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class BuildConfigKtPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // afterEvaluate required to get access to plugin internals
        target.afterEvaluate { project ->
            if (project.plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
                project.logger.info("Registering generateBuildConfigKt")
                project.tasks.register("generateBuildConfigKt", GenerateBuildConfigKtTask::class.java)
                project.logger.info("Adding buildConfig generated directory to Kotlin src")
                project.extensions.findByType(KotlinJvmProjectExtension::class.java)
                    ?.sourceSets
                    ?.findByName("main")
                    ?.kotlin
                    ?.srcDir(BuildConfigUtils.getRootOutputPath(project.buildDir.toPath()))
                project.tasks.withType(KotlinCompile::class.java).configureEach { task ->
                    task.dependsOn("generateBuildConfigKt")
                }
            }
        }
    }
}