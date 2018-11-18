import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
    id("java-gradle-plugin")
    id("maven")
}

group = "io.pixeloutlaw.gradle"
version = "0.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(kotlin("gradle-plugin"))
    implementation("com.squareup:kotlinpoet:1.0.0-RC2") {
        exclude(group = "org.jetbrains.kotlin")
    }

    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.12")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testImplementation("org.junit-pioneer:junit-pioneer:0.3.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

gradlePlugin {
    plugins {
        register("buildconfigkt") {
            id = "io.pixeloutlaw.gradle.buildconfigkt"
            implementationClass = "io.pixeloutlaw.gradle.buildconfig.BuildConfigKtPlugin"
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.withType<Wrapper> {
    gradleVersion = "4.10.2"
}