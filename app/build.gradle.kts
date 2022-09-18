plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.ceilingmeasurer"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
            }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //Essentials
    implementation(Libs.core_ktx)
    implementation(Libs.material)
    implementation(Libs.appcompat)
    implementation(Libs.constraintLayout)

    //ViewModel + LiveData
    implementation(Libs.lifecycle_livedata)
    implementation(Libs.lifecycle_viewmodel)

    //Navigation
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)

    //Room
    implementation(Libs.room_runtime)
    implementation(Libs.room_ktx)
    kapt(Libs.room_compiler)

    //Koin
    implementation(Libs.koin_core)
    implementation(Libs.koin_android)
    implementation(Libs.koin_compat)

    //Timber
    implementation(Libs.timber)
}