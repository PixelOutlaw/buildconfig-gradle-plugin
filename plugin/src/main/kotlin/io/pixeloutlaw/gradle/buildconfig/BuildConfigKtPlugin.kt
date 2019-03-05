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

        // we need to use afterEvaluate in case the Kotlin plugin is applied after we're applied
        target.afterEvaluate {
            if (plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
                logger.info("Registering generateBuildConfigKt task...")
                tasks.register("generateBuildConfigKt", GenerateBuildConfigKtTask::class.java)
                logger.info("Adding buildConfigKt generated directory to Kotlin src...")
                extensions.findByType(KotlinJvmProjectExtension::class.java)
                    ?.sourceSets?.findByName("main")
                    ?.kotlin?.srcDir(BuildConfigUtils.getRootOutputPath(target.buildDir.toPath()))
                logger.info("Adding 'generateBuildConfigKt' as a dependency for Kotlin compilation tasks...")
                tasks.withType<KotlinCompile>().configureEach {
                    dependsOn("generateBuildConfigKt")
                }
            }
        }
    }
}
