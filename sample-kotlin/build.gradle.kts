import pl.allegro.tech.build.axion.release.domain.RepositoryConfig

plugins {
    kotlin("jvm") version Versions.org_jetbrains_kotlin_jvm_gradle_plugin
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    id("pl.allegro.tech.build.axion-release") version Versions.pl_allegro_tech_build_axion_release_gradle_plugin
    id("io.pixeloutlaw.gradle.buildconfigkt")
}

group = "io.pixeloutlaw.gradle"

repositories {
    jcenter()
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk8)
}

scmVersion {
    repository(closureOf<RepositoryConfig>() {
        directory = project.rootProject.file("../")
    })
    project.version = version
}
