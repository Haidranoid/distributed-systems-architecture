package org.dsa.shared.plugin.config

import org.dsa.shared.plugin.constants.Repository
import org.gradle.api.Project

class RepositoriesConfig {
    static def load = { Project project ->
        project.repositories.add(project.repositories.mavenLocal())

        def codeArtifactAuthToken = System.getenv("AWS_CODEARTIFACT_AUTH_TOKEN")

        try {
            if (!codeArtifactAuthToken) {
                throw new RuntimeException("AWS_CODEARTIFACT_AUTH_TOKEN not found")
            }

            project.repositories.add(project.repositories.maven {
                url = Repository.AWS_CODEARTIFACT_SHARED.url
                credentials { credentials
                    credentials.username = "aws"
                    credentials.password = codeArtifactAuthToken
                }
            })
        } catch (Exception ignored) {
            project.repositories.add(project.repositories.mavenCentral())
        }
    }
}
