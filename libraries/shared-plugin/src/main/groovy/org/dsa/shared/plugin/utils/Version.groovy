package org.dsa.shared.plugin.utils

import org.dsa.shared.plugin.constants.Versions
import org.gradle.api.GradleException
import org.gradle.api.Project;

class Version {
    static def configureVersioning(Project target) {
        target.tasks.register('versionPatch') {
            doLast {
                updateVersion(Versions.PATCH, target)
            }
        }

        target.tasks.register('versionMinor') {
            doLast {
                updateVersion(Versions.MINOR, target)
            }
        }

        target.tasks.register('versionMajor') {
            doLast {
                updateVersion(Versions.MAYOR, target)
            }
        }
    }

    private static def updateVersion(Versions incrementType, Project target) {
        def gradleFile = target.file('build.gradle')
        def originalContent = gradleFile.text

        // Split the current version into major, minor, and patch components
        def versionMatch = originalContent =~ /version\s*=\s*['"](\d+)\.(\d+)\.(\d+)['"]/

        if (versionMatch) {
            def major = versionMatch[0][1] as Integer
            def minor = versionMatch[0][2] as Integer
            def patch = versionMatch[0][3] as Integer

            // Increment the version based on the specified type
            switch (incrementType) {
                case Versions.MAYOR:
                    major++
                    minor = 0  // Reset minor to 0
                    patch = 0  // Reset patch to 0
                    break
                case Versions.MINOR:
                    minor++
                    patch = 0  // Reset patch to 0
                    break
                case Versions.PATCH:
                    patch++
                    break
                default:
                    throw new GradleException("Invalid version type. Must be 'major', 'minor', or 'patch'.")
            }

            // Build the new version string
            def newVersion = "${major}.${minor}.${patch}"

            // Replace the old version with the new version in the original content
            def updatedContent = originalContent.replace(versionMatch[0][0], "version = '$newVersion'")

            // Write the updated content back to the build.gradle file
            gradleFile.text = updatedContent

            println "Version updated to: ${newVersion}"
        } else {
            throw new GradleException("Version not found in build.gradle.")
        }
    }
}
