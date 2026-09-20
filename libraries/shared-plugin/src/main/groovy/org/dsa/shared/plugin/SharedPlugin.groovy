package org.dsa.shared.plugin

import org.dsa.shared.plugin.config.SharedPluginConfig
import org.dsa.shared.plugin.extensions.SnapshotsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class SharedPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.pluginManager.apply("java-library")

        project.extensions.create("snapshots", SnapshotsExtension, project)

        project.afterEvaluate(SharedPluginConfig.load)
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