pluginManagement {
    repositories {
        google()
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

rootProject.name = "Halachic Times"
include(":android-lib:kotlin")
include(":android-lib:kvm")
include(":android-lib:lib")
include(":app")
include(":common")
include(":compass")
include(":compass-bahai")
include(":compass-jewish")
include(":compass-lib")
include(":locations")
include(":zmanim")
include(":GeoNames")
