package org.dsa.shared.plugin.extensions

import org.gradle.api.Project

class SnapshotsExtension {

    private final Project project
    final List<String> dependencies = []


    SnapshotsExtension(Project project) {
        this.project = project
    }

    def snapshot(String dependency) {
        dependencies << dependency

        project.dependencies.add("implementation", dependency)
    }

    def methodMissing(String name, Object args){
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("${name} expects exactly one dependency notation")
        }

        String dependency = args[0].toString()

        //TODO: make the opposite
        if (!project.configurations.findByName(name)) {
            throw new IllegalArgumentException("Unknown Gradle dependency configuration '${name}'")
        }

        dependencies << dependency

        project.dependencies.add(name, dependency)
    }
}
