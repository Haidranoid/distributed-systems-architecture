package org.dsa.shared.plugin.utils

import org.gradle.api.Project

class Configurations {
    static void configureConfigurations(Project target) {
        target.configurations.getByName('integrationTestImplementation').extendsFrom(
                target.configurations.getByName('testImplementation')
        )
        target.configurations.getByName('integrationTestRuntimeOnly').extendsFrom(
                target.configurations.getByName('testRuntimeOnly')
        )
    }
}
