package org.dsa.shared.plugin.utils

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

class Publish {
    static def configurePublishing(Project target) {
        // Apply required plugin
        target.pluginManager.apply("maven-publish")
        target.pluginManager.apply("java") // in case not applied

        // Wait for afterEvaluate so 'components.java' is available
        target.afterEvaluate {
            def publishing = target.extensions.getByType(PublishingExtension)

            def groupId = target.group
            def artifactId = target.name
            def version = target.version.toString()

            publishing.publications {
                mavenJava(MavenPublication) {
                    from target.components.java
                    groupId = groupId
                    artifactId = artifactId
                    version = version
                }
            }

            publishing.repositories {
                target.repositories {
                    mavenCentral()
                    mavenLocal()

                    maven {
                        url 'https://artifacts-314812911342.d.codeartifact.us-east-1.amazonaws.com/maven/shared/'
                        credentials {
                            username "aws"
                            password System.getenv("AUTH_TOKEN")
                        }
                    }
                }
            }
        }
    }
}
