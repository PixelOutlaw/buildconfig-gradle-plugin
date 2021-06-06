plugins {
    kotlin("jvm") version "1.4.31" apply false
    id("io.pixeloutlaw.gradle") version "4.4.3"
    id("io.pixeloutlaw.gradle.buildconfigkt") apply false
}

tasks.withType<Wrapper>().configureEach {
    doLast {
        copy {
            from(propertiesFile)
            into(gradle.includedBuild("buildconfig-gradle-plugin").projectDir.resolve("gradle/wrapper"))
        }
    }
}
