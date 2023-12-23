pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            content {
                includeGroupByRegex("com.android.*")
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("com.google.android.*")
            }
        }
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Automatically detect modules.
ModuleDetector.modules(rootDir).forEach { module ->
    include(module)
}

private object ModuleDetector {

    fun modules(rootDir: File) : List<String> {
        return rootDir.listDirs().flatMap { dir ->
            findModules(parent = "", dir = dir)
        }
    }

    private fun findModules(parent: String, dir: File): List<String> {
        if (dir.isDirectory.not() || dir.isProject()) {
            return emptyList()
        }

        val current: String = parent + ":" + dir.name
        return if (dir.isModule()) {
            println("include '$current'")
            listOf(current)
        } else {
            dir.listDirs().flatMap { subDir ->
                findModules(parent = current, dir = subDir)
            }
        }
    }

    private fun File.isProject(): Boolean {
        if (isDirectory.not()) {
            return false
        }
        return listFiles().orEmpty().any {
            it.isFile && (it.name == "settings.gradle" || it.name == "settings.gradle.kts")
        }
    }

    private fun File.isModule(): Boolean {
        if (isDirectory.not()) {
            return false
        }
        return listFiles().orEmpty().any {
            it.isFile && (it.name == "build.gradle" || it.name == "build.gradle.kts")
        }
    }

    private fun File.listDirs(): List<File> {
        return listFiles().orEmpty().filter { it.isDirectory }
    }
}
