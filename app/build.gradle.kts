plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.ulp.logininmo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ulp.logininmo"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        viewBinding=true

    }
}

dependencies {
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation(libs.legacy.support.v4)
    implementation(libs.google.services)
    implementation(libs.play.services.location)
    implementation(libs.play.services.maps)
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}