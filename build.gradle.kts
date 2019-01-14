import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
    id("org.gradle.kotlin.kotlin-dsl") version "1.1.1"
    id("org.gradle.java-gradle-plugin")
    id("org.gradle.maven-publish")
    id("com.gradle.build-scan") version "2.1"
    id("com.gradle.plugin-publish") version "0.10.0"
    id("pl.allegro.tech.build.axion-release") version "1.10.0"
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
    implementation(kotlin("gradle-plugin", KotlinCompilerVersion.VERSION))
    implementation("com.squareup:kotlinpoet:1.0.1") {
        exclude(group = "org.jetbrains.kotlin")
    }

    testImplementation(kotlin("test-junit5", KotlinCompilerVersion.VERSION))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testImplementation("org.junit-pioneer:junit-pioneer:0.3.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
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
