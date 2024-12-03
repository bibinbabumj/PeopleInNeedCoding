plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.bibin.babu.software.developer.peopleinneedcoding"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.bibin.babu.software.developer.peopleinneedcoding"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    ///Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")


    // Hilt Core
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    // Hilt ViewModel Integration
//    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
//    kapt ("androidx.hilt:hilt-compiler:1.0.0")

    // Lifecycle dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")



    //

//    testImplementation ("org.mockito:mockito-core:4.6.1")
//    testImplementation ("org.mockito:mockito-inline:4.6.1")
//    // For LiveData testing
//    testImplementation ("androidx.arch.core:core-testing:2.1.0")
//    // For coroutine testing
//    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
//

    // Test
    testImplementation ("com.willowtreeapps.assertk:assertk:0.26.1")
    testImplementation ("io.mockk:mockk:1.12.5")

    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.9.3")
    testImplementation ("org.junit.jupiter:junit-jupiter-params:5.9.3")

}

kapt {
    correctErrorTypes = true
}

