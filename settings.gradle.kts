rootProject.name = "buildconfig-gradle"

include("sample-kotlin")
include("sample-groovy")

includeBuild("buildconfig-gradle-plugin")

gradle.allprojects {
    group = "io.pixeloutlaw.gradle"

    repositories {
        jcenter()
    }
}
