plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "den.harbut.shelflife"
    compileSdk = 35

    defaultConfig {
        applicationId = "den.harbut.shelflife"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Compose
    implementation("androidx.compose.ui:ui:1.8.3")
    implementation("androidx.compose.material:material:1.8.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.8.3")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui-graphics")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling:1.8.3")
    implementation("androidx.navigation:navigation-compose:2.9.2")
    implementation("androidx.compose.material3:material3:1.3.2")

    implementation("com.google.accompanist:accompanist-pager:0.36.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.36.0")


    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")

    // Room
    implementation("androidx.room:room-runtime:2.7.2")
    implementation("androidx.room:room-ktx:2.7.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    kapt("androidx.room:room-compiler:2.7.2")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57")
    kapt("com.google.dagger:hilt-compiler:2.57")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    implementation("androidx.compose.material:material-icons-extended:1.7.8")
}
