@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.conte.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.androidx.runner)

    // Test
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.ext.junit.ktx)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)

    androidTestImplementation(libs.hilt.testing)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit.ktx)
    androidTestImplementation(libs.arch.core)
    androidTestImplementation(libs.coroutines.testing)
    androidTestImplementation(libs.turbine)

    // room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // hilt
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(project(":domain"))
}