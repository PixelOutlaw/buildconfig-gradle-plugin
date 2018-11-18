package io.pixeloutlaw.gradle.buildconfig

import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class BuildConfigUtilsTest {
    private val currentPath = Paths.get("").toAbsolutePath()

    @Test
    fun doesGetRootOutputPathReturnTemplatedStringForPath() {
        assertk.assert(BuildConfigUtils.getRootOutputPath(currentPath))
            .isEqualTo(currentPath.resolve("generated/source/buildConfig"))
    }

    @Test
    fun doesConvertGavToPackageNameReturnTemplatedString() {
        assertk.assert(BuildConfigUtils.convertGavToPackageName("dank.memes", "dude"))
            .isEqualTo("dank.memes.dude")
    }

    @Test
    fun doesConvertGavToPackageNameReturnTemplatedStringWithDotsReplacedWithUnderscores() {
        assertk.assert(BuildConfigUtils.convertGavToPackageName("dank.memes", "dude.man"))
            .isEqualTo("dank.memes.dude_man")
    }

    @Test
    fun doesConvertGavToPackageNameReturnTemplatedStringWithHyphensReplacedWithUnderscores() {
        assertk.assert(BuildConfigUtils.convertGavToPackageName("dank.memes", "dude-man"))
            .isEqualTo("dank.memes.dude_man")
    }

    @Test
    fun doesConvertGroupIdToPathHandleStringWithDot() {
        assertk.assert(BuildConfigUtils.convertPackageNameToPath("dank.memes"))
            .isEqualTo(Paths.get(".", "dank", "memes"))
    }

    @Test
    fun doesGetFileOutputPathReturnTemplatedStringForPath() {
        assertk.assert(BuildConfigUtils.getFileOutputPath(currentPath, "dank.memes", "dude"))
            .isEqualTo(currentPath.resolve("generated/source/buildConfig/./dank/memes/dude/BuildConfig.kt"))
    }
}