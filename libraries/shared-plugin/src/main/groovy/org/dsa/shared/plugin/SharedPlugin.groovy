package org.dsa.shared.plugin

import org.dsa.shared.plugin.constants.Repositories
import org.dsa.shared.plugin.extensions.SnapshotsExtension
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class SharedPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.pluginManager.apply("java-library")

        def snapshots = project.extensions.create("snapshots", SnapshotsExtension, project)

        project.afterEvaluate {

            if (snapshots.dependencies.isEmpty()) {
                return
            }

            project.repositories.maven {
                name = "snapshots"
                url = Repositories.AWS_CODEARTIFACT_SHARED.url

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
}


/*
def extension = target.extensions.create("bootify", SharedPluginExtension)

target.afterEvaluate {
    if (extension.enableRepositoryFetch) {
        Repository.configureRepositories(target)
    } else {
        target.logger.warn("⚠️ Skipping repositories fetch")
    }
    if (extension.enablePublish) {
        Publish.configurePublishing(target)
    } else {
        target.logger.warn("⚠️ Skipping publish configuration")
    }
}

Version.configureVersioning(target)
SourceSets.configureSourceSets(target)
Configurations.configureConfigurations(target)

*/