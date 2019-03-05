import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val com_adarshr_test_logger_gradle_plugin: String = "1.6.0" 

    const val com_diffplug_gradle_spotless_gradle_plugin: String = "3.18.0" // available: "3.18.0"

    const val com_gradle_build_scan_gradle_plugin: String = "2.1" // available: "2.2.1"

    const val com_gradle_plugin_publish_gradle_plugin: String = "0.10.1" // available: "0.10.1"

    const val kotlinpoet: String = "1.1.0" // available: "1.1.0"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val org_gradle_kotlin_kotlin_dsl_gradle_plugin: String = "1.1.1" // available: "1.2.5"

    const val org_jetbrains_kotlin: String = "1.3.21" // available: "1.3.21"

    const val junit_pioneer: String = "0.3.0" 

    const val org_junit_jupiter: String = "5.4.0" // available: "5.4.0"

    const val mockito_junit_jupiter: String = "2.24.5" // available: "2.24.5"

    const val pl_allegro_tech_build_axion_release_gradle_plugin: String = "1.10.0" 

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.1.1"

        const val currentVersion: String = "5.2.1"

        const val nightlyVersion: String = "5.4-20190305000052+0000"

        const val releaseCandidate: String = ""
    }
}