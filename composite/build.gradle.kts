import pl.allegro.tech.build.axion.release.domain.RepositoryConfig
import pl.allegro.tech.build.axion.release.domain.hooks.HookContext
import pl.allegro.tech.build.axion.release.domain.hooks.HooksConfig

plugins {
    idea
    id("de.fayard.buildSrcVersions") version "0.3.2"
    id("pl.allegro.tech.build.axion-release") version "1.10.0"
}

group = "io.pixeloutlaw.gradle"

scmVersion {
    repository(closureOf<RepositoryConfig>() {
        directory = project.rootProject.file("../")
    })
    hooks(closureOf<HooksConfig>() {
        pre("fileUpdate", mapOf(
            "file" to "../README.md",
            "pattern" to closureOf<String, HookContext, String>() { v, _ ->
                "id(\"io.pixeloutlaw.gradle.buildconfigkt\") version \"$v\""
            },
            "replacement" to closureOf<String, HookContext, String>() { v, _ ->
                "id(\"io.pixeloutlaw.gradle.buildconfigkt\") version \"$v\""
            }
        ))
        pre("commit")
    })
    project.version = version
}

tasks.register("publish") {
    group = "Custom"
    description = "Publish the plugin locally"
    dependsOn(gradle.includedBuild("buildConfigKt").task(":publish"))
}
tasks.register("publishPlugins") {
    group = "Custom"
    description = "Publishes this plugin to the Gradle Plugin portal."
    dependsOn(gradle.includedBuild("buildConfigKt").task(":publishPlugins"))
}

tasks.register("checkAll") {
    group = "Custom"
    description = "Run all checks"
    dependsOn(gradle.includedBuild("plugin").task(":validateTaskProperties"))
    dependsOn(gradle.includedBuild("plugin").task(":check"))
    dependsOn(gradle.includedBuild("sample-groovy").task(":generateBuildConfigKt"))
    dependsOn(gradle.includedBuild("sample-kotlin").task(":generateBuildConfigKt"))
}

tasks.register("cleanAll") {
    group = "Custom"
    description = "Run all cleans"
    dependsOn(gradle.includedBuild("plugin").task(":clean"))
    dependsOn(gradle.includedBuild("sample-groovy").task(":clean"))
    dependsOn(gradle.includedBuild("sample-kotlin").task(":clean"))
}

tasks.register("buildSrcVersionsAll") {
    group = "Custom"
    description = "Run all buildSrcVersions"
    dependsOn(gradle.includedBuild("buildConfigKt").task(":buildSrcVersions"))
    dependsOn(gradle.includedBuild("sample-groovy").task(":buildSrcVersions"))
    dependsOn(gradle.includedBuild("sample-kotlin").task(":buildSrcVersions"))
}
