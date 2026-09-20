package org.dsa.shared.plugin.config

import org.dsa.shared.plugin.constants.Repository
import org.dsa.shared.plugin.extensions.SnapshotsExtension
import org.gradle.api.GradleException
import org.gradle.api.Project

class SnapshotsConfig {
    static def load = { Project project ->
        def snapshots = project.extensions.getByType(SnapshotsExtension)

        if (snapshots.dependencies.isEmpty()) {
            return
        }

        project.repositories.maven {
            name = "snapshots"
            url = Repository.AWS_CODEARTIFACT_SHARED.url

            credentials { credentials ->
                credentials.username = "aws"
                credentials.password = System.getenv("AWS_CODEARTIFACT_AUTH_TOKEN")
            }

            content { contentDescriptor ->
                snapshots.dependencies.each { dependency ->

                    def dependencyParts = dependency.split(":")

                    if (dependencyParts.size() != 3) {
                        throw new GradleException("Invalid snapshot dependency '${dependency}'. Expected: group:artifact:version")
                    }

                    contentDescriptor.includeVersion(dependencyParts[0], dependencyParts[1], dependencyParts[2])
                }
            }
        }
    }
}
