import pl.allegro.tech.build.axion.release.domain.RepositoryConfig

plugins {
    kotlin("jvm") version "1.3.21"
    id("de.fayard.buildSrcVersions") version "0.3.2"
    id("pl.allegro.tech.build.axion-release") version "1.10.0"
    id("io.pixeloutlaw.gradle.buildconfigkt")
}

group = "io.pixeloutlaw.gradle"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

scmVersion {
    repository(closureOf<RepositoryConfig>() {
        directory = project.rootProject.file("../")
    })
    project.version = version
}
