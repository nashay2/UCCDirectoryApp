plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.uccdirectoryapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.uccdirectoryapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Core Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Other Libraries
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material3:material3:1.0.0")
    implementation("androidx.navigation:navigation-compose:2.5.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0")
    implementation("androidx.activity:activity-compose:1.6.0")
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.0")
}
