plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    compileSdk = libs.versions.compileSdk.toInt()
    namespace = "com.github.times.compass.lib"

    defaultConfig {
        minSdk = libs.versions.minSdk.toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            proguardFile("proguard-rules.pro")
            consumerProguardFiles("proguard-rules.pro")
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = BuildVersions.jvm
        targetCompatibility = BuildVersions.jvm
    }

    kotlinOptions {
        jvmTarget = BuildVersions.jvm.toString()
    }

    lint {
        disable += "LocaleFolder"
        disable += "UnusedResources"
    }
}

dependencies {
    implementation(project(":android-lib:lib"))
    implementation(project(":common"))
    implementation(project(":locations"))

    // Testing
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.test.android)
}
