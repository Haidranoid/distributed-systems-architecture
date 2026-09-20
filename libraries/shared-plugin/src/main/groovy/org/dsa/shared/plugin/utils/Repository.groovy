package org.dsa.shared.plugin.utils

import org.dsa.shared.plugin.constants.Repositories
import org.gradle.api.Project

class Repository {
    static def configureRepositories(Project target) {
        target.repositories {
            mavenCentral()
            mavenLocal()

            maven {
                url = Repositories.BACKEND_CORE.url
                credentials {
                    username = "aws"
                    password = System.getenv("AUTH_TOKEN")
                }
            }

            maven {
                url = Repositories.COMMON_UTILS.url
                credentials {
                    username = "aws"
                    password = System.getenv("AUTH_TOKEN")
                }
            }
        }
    }
}