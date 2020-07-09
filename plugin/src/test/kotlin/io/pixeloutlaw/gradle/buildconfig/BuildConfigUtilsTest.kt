package io.pixeloutlaw.gradle.buildconfig

import com.google.common.truth.Truth.assertThat
import java.nio.file.Paths
import org.junit.jupiter.api.Test

class BuildConfigUtilsTest {
    private val currentPath = Paths.get("").toAbsolutePath()

    @Test
    fun doesGetRootOutputPathReturnTemplatedStringForPath() {
        assertThat(
            currentPath.resolve("generated-sources/buildConfigKt")
        ).isEqualTo(
            BuildConfigUtils.getRootOutputPath(currentPath)
        )
    }

    @Test
    fun doesCovertGavToPackageNameReturnStartingWithNoDotForEmptyPackage() {
        assertThat("dude").isEqualTo(BuildConfigUtils.convertGavToPackageName("", "dude"))
    }

    @Test
    fun doesCovertGavToPackageNameReturnStartingWithNoDotForSpacePackage() {
        assertThat("dude").isEqualTo(BuildConfigUtils.convertGavToPackageName(" ", "dude"))
    }

    @Test
    fun doesConvertGavToPackageNameReturnTemplatedString() {
        assertThat("dank.memes.dude").isEqualTo(BuildConfigUtils.convertGavToPackageName("dank.memes", "dude"))
    }

    @Test
    fun doesConvertGavToPackageNameReturnTemplatedStringWithDotsReplacedWithUnderscores() {
        assertThat("dank.memes.dude_man").isEqualTo(BuildConfigUtils.convertGavToPackageName("dank.memes", "dude.man"))
    }

    @Test
    fun doesConvertGavToPackageNameReturnTemplatedStringWithHyphensReplacedWithUnderscores() {
        assertThat("dank.memes.dude_man").isEqualTo(BuildConfigUtils.convertGavToPackageName("dank.memes", "dude-man"))
    }

    @Test
    fun doesConvertGroupIdToPathHandleStringWithDot() {
        assertThat(Paths.get(".", "dank", "memes")).isEqualTo(BuildConfigUtils.convertPackageNameToPath("dank.memes"))
    }
}
