plugins {
    kotlin("jvm")
    id("io.pixeloutlaw.gradle.buildconfigkt")
}

group = "io.pixeloutlaw.gradle"

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}
