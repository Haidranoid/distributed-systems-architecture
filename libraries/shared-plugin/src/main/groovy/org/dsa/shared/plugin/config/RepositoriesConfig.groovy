package org.dsa.shared.plugin.config

import org.gradle.api.Project

class RepositoriesConfig {
    static def load = { Project project ->
        project.repositories.add(project.repositories.mavenLocal())
        project.repositories.add(project.repositories.mavenCentral())

        project.repositories.add(
                project.repositories.maven {
                    try {
                        def codeArtifactAuthToken = System.getenv("AWS_CODEARTIFACT_AUTH_TOKEN")

                        if (!codeArtifactAuthToken) {

                            codeArtifactAuthToken = project.providers.exec {
                                commandLine 'aws', 'codeartifact', 'get-authorization-token',
                                        '--domain', 'artifacts',
                                        '--query', 'authorizationToken',
                                        '--output', 'text'
                            }.standardOutput.asText.get().trim()
                        }

                        url 'https://artifacts-314812911342.d.codeartifact.us-east-1.amazonaws.com/maven/shared/'
                        credentials {
                            it.username = "aws"
                            it.password = codeArtifactAuthToken
                        }

                    } catch (Exception ignored) {
                        project.repositories.mavenCentral()
                    }
                })
    }
}
