/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.github.minigdx.gradle.plugin

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * A simple functional test for the 'com.github.minigdx.gradle.plugin.greeting' plugin.
 */
class MinigdxDeveloperPluginFunctionalTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = TemporaryFolder()

    @Test fun `can create github workflow`() {
        // Setup the test build
        val projectDir = temporaryFolder.newFolder("build", "functionalTest")
        projectDir.mkdirs()
        projectDir.resolve("settings.gradle").writeText("""
            plugins {
                id("org.gradle.toolchains.foojay-resolver-convention") version("0.4.0")
            }
        """.trimIndent())

        projectDir.resolve("build.gradle").writeText("""
            plugins {
                id('com.github.minigdx.gradle.plugin.developer')
            }
        """)

        // Run the build
        val runner = GradleRunner.create()
        runner.forwardOutput()
        runner.withPluginClasspath()
        runner.withArguments("createGithubWorkflows")
        runner.withProjectDir(projectDir)
        val result = runner.build()

        // Verify the result
        assertEquals(TaskOutcome.SUCCESS, result.task(":createGithubWorkflows")?.outcome)
        val fileCreated = projectDir.resolve(".github/workflows/build.yml").exists()
        assertTrue(fileCreated)
    }

    @Test fun `can create makefile`() {
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
                id('com.github.minigdx.gradle.plugin.developer')
            }
        """)

        // Run the build
        val runner = GradleRunner.create()
        runner.forwardOutput()
        runner.withPluginClasspath()
        runner.withArguments("createMakefile")
        runner.withProjectDir(projectDir)
        val result = runner.build()

        // Verify the result
        assertEquals(TaskOutcome.SUCCESS, result.task(":createMakefile")?.outcome)
        val fileCreated = projectDir.resolve("Makefile").exists()
        assertTrue(fileCreated)
    }
}
