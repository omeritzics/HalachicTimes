import com.android.build.api.dsl.LibraryDefaultConfig
import java.util.Base64

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

fun LibraryDefaultConfig.encodeApiKey(name: String) {
    val value = project.properties[name].toString()
    val bytes = value.encodeToByteArray()
    val encoded = Base64.getEncoder().encodeToString(bytes)
    buildConfigField("String", name, "\"" + encoded + "\"")
}

android {
    compileSdk = libs.versions.compileSdk.toInt()
    namespace = "com.github.times.location"

    defaultConfig {
        minSdk = libs.versions.minSdk.toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        encodeApiKey("BING_API_KEY")
        encodeApiKey("GEONAMES_USERNAME")
        encodeApiKey("GOOGLE_API_KEY")
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            proguardFile("proguard-rules.pro")
            consumerProguardFiles("proguard-rules.pro")
        }
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
        disable += "RtlHardcoded"
        disable += "UnusedAttribute"
        disable += "UnusedResources"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":android-lib:lib"))
    implementation(project(":common"))

    // Maps
    implementation(libs.google.maps)
    implementation(libs.cardview)

    // Background tasks
    implementation(libs.work.runtime)

    testImplementation(libs.bundles.test)
    testImplementation(kotlin("reflect"))
    androidTestImplementation(libs.bundles.test.android)
}
