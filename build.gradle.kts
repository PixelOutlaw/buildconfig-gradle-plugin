import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.org_jetbrains_kotlin_jvm_gradle_plugin
    id("org.gradle.kotlin.kotlin-dsl") version Versions.org_gradle_kotlin_kotlin_dsl_gradle_plugin
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    id("org.gradle.java-gradle-plugin")
    id("org.gradle.maven-publish")
    id("com.gradle.build-scan") version Versions.com_gradle_build_scan_gradle_plugin
    id("com.gradle.plugin-publish") version Versions.com_gradle_plugin_publish_gradle_plugin
    id("pl.allegro.tech.build.axion-release") version Versions.pl_allegro_tech_build_axion_release_gradle_plugin
}

group = "io.pixeloutlaw.gradle"
version = scmVersion.version

repositories {
    jcenter()
}

gradlePlugin {
    plugins {
        register("buildconfigkt") {
            id = "io.pixeloutlaw.gradle.buildconfigkt"
            implementationClass = "io.pixeloutlaw.gradle.buildconfig.BuildConfigKtPlugin"
        }
    }
}

dependencies {
    implementation(Libs.kotlin_gradle_plugin)
    implementation(Libs.kotlinpoet) {
        exclude(group = "org.jetbrains.kotlin")
    }

    testImplementation(Libs.kotlin_test_junit5)
    testImplementation(Libs.junit_jupiter_api)
    testImplementation(Libs.junit_pioneer)
    testRuntimeOnly(Libs.junit_jupiter_engine)
}

pluginBundle {
    website = "https://github.com/PixelOutlaw/buildconfig-gradle-plugin"
    vcsUrl = "https://github.com/PixelOutlaw/buildconfig-gradle-plugin"

    description = "Generates a BuildConfig.kt class."

    (plugins) {
        "buildconfigkt" {
            displayName = "BuildConfigKt"
            tags = listOf("kotlin", "build", "config", "buildconfig")
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform { }
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED, TestLogEvent.STANDARD_OUT, TestLogEvent.STANDARD_ERROR)
    }
}
tasks.withType<Wrapper>().configureEach {
    gradleVersion = "5.1.1"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
    publishAlways()
}
