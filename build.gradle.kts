plugins {
    kotlin("jvm") version "1.4.30" apply false
    id("io.pixeloutlaw.multi") version "2.0.7"
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
