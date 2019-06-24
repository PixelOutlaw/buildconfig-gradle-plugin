import kotlin.String

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions`
 */
object Libs {
  const val de_fayard_buildsrcversions_gradle_plugin: String =
      "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
      Versions.de_fayard_buildsrcversions_gradle_plugin

  const val org_jetbrains_kotlin_jvm_gradle_plugin: String =
      "org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:" +
      Versions.org_jetbrains_kotlin_jvm_gradle_plugin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_scripting_compiler_embeddable: String =
      "org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:" + Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_stdlib_jdk8: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:" +
      Versions.org_jetbrains_kotlin

  const val pl_allegro_tech_build_axion_release_gradle_plugin: String =
      "pl.allegro.tech.build.axion-release:pl.allegro.tech.build.axion-release.gradle.plugin:" +
      Versions.pl_allegro_tech_build_axion_release_gradle_plugin
}
