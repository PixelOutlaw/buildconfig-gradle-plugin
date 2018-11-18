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
    fun doesConvertGroupIdToPathHandleStringWithDot() {
        assertk.assert(BuildConfigUtils.convertGroupIdToPath("dank.memes"))
            .isEqualTo(Paths.get(".", "dank", "memes"))
    }

    @Test
    fun doesGetFileOutputPathReturnTemplatedStringForPath() {
        assertk.assert(BuildConfigUtils.getFileOutputPath(currentPath, "dank.memes"))
            .isEqualTo(currentPath.resolve("generated/source/buildConfig/./dank/memes/BuildConfig.kt"))
    }
}