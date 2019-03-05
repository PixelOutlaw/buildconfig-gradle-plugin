import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val org_jetbrains_kotlin_jvm_gradle_plugin: String = "1.3.21" 

    const val org_jetbrains_kotlin: String = "1.3.21" 

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
