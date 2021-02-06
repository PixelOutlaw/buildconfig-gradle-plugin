plugins {
    kotlin("jvm") version "1.4.21" apply false
    id("io.pixeloutlaw.multi") version "2.0.6"
    id("io.pixeloutlaw.gradle.buildconfigkt") apply false
}

tasks.withType<Wrapper>().configureEach {
    version = "6.8.2"
    doLast {
        copy {
            from(propertiesFile)
            into(gradle.includedBuild("pixeloutlaw-gradle-plugin").projectDir.resolve("gradle/wrapper"))
        }
    }
}
