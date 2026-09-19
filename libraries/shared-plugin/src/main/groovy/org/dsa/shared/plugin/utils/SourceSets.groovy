package org.dsa.shared.plugin.utils


import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.testing.Test

class SourceSets {
    static void configureSourceSets(Project target) {
        target.pluginManager.apply("java")

        def javaExtension = target.extensions.getByType(JavaPluginExtension)
        def sourceSets = javaExtension.sourceSets as SourceSetContainer

        if (!sourceSets.findByName("integrationTest")) {
            setIntegrationTest(target, sourceSets)
        }

        if (!target.tasks.findByName('integrationTest')) {
            target.tasks.register('integrationTest', Test)
        }

        target.tasks.named('integrationTest', Test).configure { Test testTask ->
            description = 'Runs the integration tests.'
            group = 'verification'

            testClassesDirs = sourceSets.integrationTest.output.classesDirs
            classpath = sourceSets.integrationTest.runtimeClasspath
            shouldRunAfter(target.tasks.named('test'))

            useJUnitPlatform() // IMPORTANT: Ensure it's using JUnit 5
        }

        target.tasks.named('check').configure { checkTask ->
            checkTask.dependsOn(target.tasks.named('integrationTest'))
        }
    }

    private static void setIntegrationTest(Project target, SourceSetContainer sourceSets) {
        sourceSets.create("integrationTest") {
            java {
                srcDir 'src/integrationTest/java'
            }
            resources {
                srcDir 'src/integrationTest/resources'
            }

            // Access configurations via target
            compileClasspath += sourceSets.main.output + target.configurations.testRuntimeClasspath
            runtimeClasspath += output + compileClasspath
        }
    }
}
