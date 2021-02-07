plugins {
    kotlin("jvm") version "1.4.30" apply false
    id("io.pixeloutlaw.gradle") version "3.0.0"
    id("io.pixeloutlaw.gradle.buildconfigkt") apply false
}

tasks.withType<Wrapper>().configureEach {
    version = "6.8.2"
    doLast {
        copy {
            from(propertiesFile)
            into(gradle.includedBuild("buildconfig-gradle-plugin").projectDir.resolve("gradle/wrapper"))
        }
    }
}
