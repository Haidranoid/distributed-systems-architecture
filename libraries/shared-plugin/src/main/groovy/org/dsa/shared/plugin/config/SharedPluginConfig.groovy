package org.dsa.shared.plugin.config

import org.gradle.api.Project

class SharedPluginConfig {
    static def load = { Project project ->
        SnapshotsConfig.load(project)
        UpdateVersionConfig.load(project)
    }
}
