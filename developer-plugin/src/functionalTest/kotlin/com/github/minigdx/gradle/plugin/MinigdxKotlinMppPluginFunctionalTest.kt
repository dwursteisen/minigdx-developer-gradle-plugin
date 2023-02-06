/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.github.minigdx.gradle.plugin

import java.io.File
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Ignore
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * A simple functional test for the 'com.github.minigdx.gradle.plugin.greeting' plugin.
 */
class MinigdxKotlinMppPluginFunctionalTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = TemporaryFolder()

    @Test fun `can build`() {
        // Setup the test build
        val projectDir = temporaryFolder.newFolder("build", "functionalTest")
        projectDir.mkdirs()
        projectDir.resolve("settings.gradle").writeText("""
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.4.0")
}
""")
        projectDir.resolve("build.gradle").writeText("""
            plugins {
                id('com.github.minigdx.gradle.plugin.developer.mpp')
            }
        """)

        // Run the build
        val result = GradleRunner.create()
            .forwardOutput()
            .withPluginClasspath()
            .withArguments("build")
            .withProjectDir(projectDir)
            .build()

        // Verify the result
        assertEquals(TaskOutcome.SUCCESS, result.task(":build")?.outcome)
    }

    @Test
    @Ignore("Configuration Cache not yet supported by Kotlin Multiplatform")
    fun `can build with configuration cache`() {
        // Setup the test build
        val projectDir = temporaryFolder.newFolder("build", "functionalTest")
        projectDir.mkdirs()
        projectDir.resolve("settings.gradle").writeText("""
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.4.0")
}
""")
        projectDir.resolve("build.gradle").writeText("""
            plugins {
                id('com.github.minigdx.gradle.plugin.developer.mpp')
            }
        """)

        // Run the build
        GradleRunner.create()
            .forwardOutput()
            .withPluginClasspath()
            .withArguments("build", "--configuration-cache")
            .withProjectDir(projectDir)
            .build()

        val result = GradleRunner.create()
        .forwardOutput()
        .withPluginClasspath()
        .withArguments("build", "--configuration-cache")
        .withProjectDir(projectDir)
       .build()

        // Verify the result
        assertTrue(result.output.contains("Reusing configuration cache."))
    }
}
