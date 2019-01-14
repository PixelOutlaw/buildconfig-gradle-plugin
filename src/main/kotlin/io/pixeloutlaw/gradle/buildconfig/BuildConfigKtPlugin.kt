package io.pixeloutlaw.gradle.buildconfig

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getting
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class BuildConfigKtPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // afterEvaluate required to get access to project internals
        target.afterEvaluate {
            if (target.plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
                target.logger.info("Registering buildConfigKt extension...")
                target.extensions.create<BuildConfigKtExtension>(
                    "buildConfigKt",
                    target.name,
                    target.version.toString(),
                    target.group.toString()
                )
                target.logger.info("Registering generateBuildConfigKt task...")
                target.tasks.register("generateBuildConfigKt", GenerateBuildConfigKtTask::class.java)
                target.logger.info("Adding buildConfigKt generated directory to Kotlin src...")
                target.extensions.findByType(KotlinJvmProjectExtension::class.java)!!
                    .sourceSets.findByName("main")
                    ?.kotlin?.srcDir(BuildConfigUtils.getRootOutputPath(target.buildDir.toPath()))
                target.tasks.getting(KotlinCompile::class) {
                    dependsOn("generateBuildConfigKt")
                }
            }
        }
    }
}