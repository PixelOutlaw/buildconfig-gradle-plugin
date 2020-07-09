import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import pl.allegro.tech.build.axion.release.domain.RepositoryConfig
import pl.allegro.tech.build.axion.release.domain.hooks.HooksConfig

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    id("com.gradle.plugin-publish") version Versions.com_gradle_plugin_publish_gradle_plugin
    id("pl.allegro.tech.build.axion-release") version Versions.pl_allegro_tech_build_axion_release_gradle_plugin
    id("com.diffplug.gradle.spotless") version Versions.com_diffplug_gradle_spotless_gradle_plugin
    id("io.gitlab.arturbosch.detekt") version Versions.io_gitlab_arturbosch_detekt
}

group = "io.pixeloutlaw.gradle"

repositories {
    jcenter()
}

dependencies {
    implementation(Libs.kotlin_gradle_plugin)
    implementation(Libs.kotlinpoet) {
        exclude(group = "org.jetbrains.kotlin")
    }

    testImplementation(Libs.junit_jupiter)
    testImplementation(Libs.mockito_junit_jupiter)
    testImplementation(Libs.truth)
}

scmVersion {
    repository(closureOf<RepositoryConfig>() {
        directory = project.rootProject.file("../")
    })
    hooks(closureOf<HooksConfig>() {
        pre(
            "fileUpdate",
            mapOf<String, Any>(
                "file" to "README.md",
                "pattern" to { v, _ -> "id(\"io.pixeloutlaw.gradle.buildconfigkt\") version \"$v\"" },
                "replacement" to { v, _ -> "id(\"io.pixeloutlaw.gradle.buildconfigkt\") version \"$v\"" }
            )
        )
        pre("commit")
    })
    project.version = version
}

gradlePlugin {
    plugins {
        create("buildConfigKt") {
            id = "io.pixeloutlaw.gradle.buildconfigkt"
            displayName = "buildConfigKt"
            description = "Painless build metadata available at runtime"
            implementationClass = "io.pixeloutlaw.gradle.buildconfig.BuildConfigKtPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/PixelOutlaw/buildconfig-gradle-plugin"
    vcsUrl = "https://github.com/PixelOutlaw/buildconfig-gradle-plugin"
    tags = listOf("kotlin", "build", "config", "buildconfig")
}

spotless {
    kotlin {
        ktlint()
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        ktlint()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<Wrapper>().configureEach {
    gradleVersion = "6.5"
}

tasks.withType<KotlinCompile>().configureEach {
    dependsOn("spotlessApply")
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform { }
    systemProperty("current.gradle.version", System.getenv("GRADLE_CURRENT_VERSION") ?: gradle.gradleVersion)
}
