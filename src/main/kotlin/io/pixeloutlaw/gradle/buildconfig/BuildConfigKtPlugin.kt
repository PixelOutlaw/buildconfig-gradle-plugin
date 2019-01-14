package io.pixeloutlaw.gradle.buildconfig

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class BuildConfigKtPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.logger.info("Creating buildConfigKt extension...")
        target.extensions.create<BuildConfigKtExtension>("buildConfigKt")
        target.logger.info("Registering generateBuildConfigKt task...")
        target.tasks.register("generateBuildConfigKt", GenerateBuildConfigKtTask::class.java)
        target.logger.info("Adding buildConfigKt generated directory to Kotlin src (if it exists)...")
        target.extensions.findByType(KotlinJvmProjectExtension::class.java)
            ?.sourceSets?.findByName("main")
            ?.kotlin?.srcDir(BuildConfigUtils.getRootOutputPath(target.buildDir.toPath()))
        target.logger.info("Adding 'generateBuildConfigKt' to Kotlin compilation tasks (if any exist)...")
        target.tasks.withType<KotlinCompile>().configureEach {
            dependsOn("generateBuildConfigKt")
        }
    }
}