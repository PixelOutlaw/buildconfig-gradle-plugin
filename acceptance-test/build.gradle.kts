plugins {
    kotlin("jvm")
    id("org.gradle.java-gradle-plugin")
    id("com.adarshr.test-logger")
}

dependencies {
    testImplementation(Libs.kotlin_test_junit5)
    testImplementation(Libs.junit_jupiter_api)
    testImplementation(Libs.junit_pioneer)
    testRuntimeOnly(Libs.junit_jupiter_engine)
}

gradlePlugin {
    pluginSourceSet(parent?.sourceSets?.main?.get())
}
