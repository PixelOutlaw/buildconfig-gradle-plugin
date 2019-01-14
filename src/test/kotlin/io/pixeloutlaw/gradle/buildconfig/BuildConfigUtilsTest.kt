package io.pixeloutlaw.gradle.buildconfig

import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

class BuildConfigUtilsTest {
    private val currentPath = Paths.get("").toAbsolutePath()

    @Test
    fun doesGetRootOutputPathReturnTemplatedStringForPath() {
        assertEquals(
            currentPath.resolve("generated/source/buildConfigKt"),
            BuildConfigUtils.getRootOutputPath(currentPath)
        )
    }

    @Test
    fun doesConvertGavToPackageNameReturnTemplatedString() {
        assertEquals("dank.memes.dude", BuildConfigUtils.convertGavToPackageName("dank.memes", "dude"))
    }

    @Test
    fun doesConvertGavToPackageNameReturnTemplatedStringWithDotsReplacedWithUnderscores() {
        assertEquals("dank.memes.dude_man", BuildConfigUtils.convertGavToPackageName("dank.memes", "dude.man"))
    }

    @Test
    fun doesConvertGavToPackageNameReturnTemplatedStringWithHyphensReplacedWithUnderscores() {
        assertEquals("dank.memes.dude_man", BuildConfigUtils.convertGavToPackageName("dank.memes", "dude-man"))
    }

    @Test
    fun doesConvertGroupIdToPathHandleStringWithDot() {
        assertEquals(Paths.get(".", "dank", "memes"), BuildConfigUtils.convertPackageNameToPath("dank.memes"))
    }
}