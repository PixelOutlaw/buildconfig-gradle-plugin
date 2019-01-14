package io.pixeloutlaw.gradle.buildconfig

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getting
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class BuildConfigKtPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // afterEvaluate required to get access to plugin internals
        target.afterEvaluate {
            if (this.plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
                this.logger.info("Registering buildConfigKt extension...")
                this.extensions.create<BuildConfigKtExtension>("buildConfigKt", target.name, target.version, target.group)
                this.logger.info("Registering generateBuildConfigKt task...")
                this.tasks.register("generateBuildConfigKt", GenerateBuildConfigKtTask::class.java)
                this.logger.info("Adding buildConfigKt generated directory to Kotlin src...")
                this.extensions.findByType(KotlinJvmProjectExtension::class.java)
                    ?.sourceSets?.findByName("main")
                    ?.kotlin?.srcDir(BuildConfigUtils.getRootOutputPath(project.buildDir.toPath()))
                this.tasks.getting(KotlinCompile::class) {
                    dependsOn("generateBuildConfigKt")
                }
            }
        }
    }
}