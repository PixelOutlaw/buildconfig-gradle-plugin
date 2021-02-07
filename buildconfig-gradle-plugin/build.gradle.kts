plugins {
    `kotlin-dsl`
    id("io.pixeloutlaw.gradle")
    id("com.gradle.plugin-publish")
}

group = "io.pixeloutlaw.gradle"

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
    implementation("com.squareup:kotlinpoet:_") {
        exclude(group = "org.jetbrains.kotlin")
    }

    testImplementation("org.junit.jupiter:junit-jupiter:_")
    testImplementation("com.google.truth:truth:_")
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
