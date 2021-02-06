rootProject.name = "buildConfigKt-composite"

include("sample-kotlin")
include("sample-groovy")

includeBuild("plugin")

gradle.allprojects {
    group = "io.pixeloutlaw.gradle"

    repositories {
        jcenter()
    }
}
