import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val com_adarshr_test_logger_gradle_plugin: String = "1.7.0" 

    const val com_diffplug_gradle_spotless_gradle_plugin: String = "3.23.1" 

    const val truth: String = "1.0" 

    const val com_gradle_build_scan_gradle_plugin: String = "2.3" 

    const val com_gradle_plugin_publish_gradle_plugin: String = "0.10.1" 

    const val kotlinpoet: String = "1.3.0" 

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val io_gitlab_arturbosch_detekt: String = "1.0.0-RC15" 

    const val org_gradle_kotlin_kotlin_dsl_gradle_plugin: String = "1.2.8" // available: "1.2.9"

    const val kotlin_compiler_embeddable: String = "1.3.31" // available: "1.3.41"

    const val kotlin_gradle_plugin: String = "1.3.21" // available: "1.3.41"

    const val kotlin_reflect: String = "1.3.31" // available: "1.3.41"

    const val kotlin_sam_with_receiver: String = "1.3.31" // available: "1.3.41"

    const val kotlin_scripting_compiler_embeddable: String = "1.3.31" // available: "1.3.41"

    const val kotlin_stdlib_jdk8: String = "1.3.31" // available: "1.3.41"

    const val junit_jupiter: String = "5.5.1" 

    const val mockito_junit_jupiter: String = "3.0.0" 

    const val pl_allegro_tech_build_axion_release_gradle_plugin: String = "1.10.1" 

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.5.1"

        const val currentVersion: String = "5.5.1"

        const val nightlyVersion: String = "5.7-20190720220029+0000"

        const val releaseCandidate: String = ""
    }
}
