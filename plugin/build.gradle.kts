plugins {
    `kotlin-dsl`
    `maven-publish`
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    id("com.gradle.plugin-publish")
}

group = "io.pixeloutlaw.gradle"

repositories {
    gradlePluginPortal()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
    implementation("com.squareup:kotlinpoet:_") {
        exclude(group = "org.jetbrains.kotlin")
    }

    testImplementation("org.junit.jupiter:junit-jupiter:_")
    testImplementation("com.google.truth:truth:_")
}

detekt {
    baseline = file("baseline.xml")
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

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

pluginBundle {
    website = "https://github.com/PixelOutlaw/buildconfig-gradle-plugin"
    vcsUrl = "https://github.com/PixelOutlaw/buildconfig-gradle-plugin"
    tags = listOf("kotlin", "build", "config", "buildconfig")
}

tasks.getByName("javadocJar", Jar::class) {
    dependsOn("dokkaJavadoc")
    from(buildDir.resolve("dokka/javadoc"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
    dependsOn("ktlintFormat")
    kotlinOptions.jvmTarget = "1.8"
}
