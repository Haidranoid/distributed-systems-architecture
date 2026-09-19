package org.dsa.shared.plugin

import org.dsa.shared.plugin.extensions.SharedPluginExtension
import org.dsa.shared.plugin.utils.*
import org.gradle.api.Plugin
import org.gradle.api.Project

class SharedPlugin implements Plugin<Project> {
    @Override
    void apply(Project target) {
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
    }
}