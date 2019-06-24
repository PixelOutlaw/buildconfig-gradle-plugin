import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
  const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

  const val org_jetbrains_kotlin_jvm_gradle_plugin: String = "1.3.40" 

  const val org_jetbrains_kotlin: String = "1.3.40" 

  const val pl_allegro_tech_build_axion_release_gradle_plugin: String = "1.10.1" 

  /**
   *
   *   To update Gradle, edit the wrapper file at path:
   *      ./gradle/wrapper/gradle-wrapper.properties
   */
  object Gradle {
    const val runningVersion: String = "5.4.1"

    const val currentVersion: String = "5.4.1"

    const val nightlyVersion: String = "5.6-20190624000056+0000"

    const val releaseCandidate: String = "5.5-rc-3"
  }
}
