pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PokeMMOTeamBuilder"

include(":app")
include(":core:common")
include(":core:database")
include(":core:network")
include(":core:domain")
include(":core:ui")
include(":feature:teambuilder")
include(":feature:pokedex")
include(":feature:damage-calc")
include(":feature:taxonomy")
