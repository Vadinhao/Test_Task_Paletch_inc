plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.test_task_paletch_inc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.test_task_paletch_inc"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    dataBinding {
        enable = true
    }

    viewBinding {
        enable = true
    }

    kapt {
        generateStubs = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.room:room-common:2.5.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit with Moshi Converter
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.9.3")
    // Coil
    implementation("io.coil-kt:coil:1.1.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    // Kapt
    annotationProcessor("com.android.databinding:compiler:3.1.4")
    //Nav
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.7.3")
    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.3")
    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:2.7.3")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // Room
    implementation("androidx.room:room-ktx:2.5.2")
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-compiler:2.5.2")
    //Koin
    //implementation("io.insert-koin:koin-android:3.1.2")
    //Dagger
    //implementation ("com.google.dagger:dagger:2.48")
    //implementation ("com.google.dagger:dagger-compiler:2.48")
}