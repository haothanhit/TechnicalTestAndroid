import dependencies.UiDep

plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.navigationSafArgs)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.dagger)
}

android {
    compileSdkVersion(Config.Android.androidCompileSdkVersion)
    buildToolsVersion(Config.Android.androidBuildToolsVersion)

    defaultConfig {
        applicationId(Environments.Release.appId)
        minSdkVersion(Config.Android.androidMinSdkVersion)
        targetSdkVersion(Config.Android.androidTargetSdkVersion)
        versionCode(Environments.Release.appVersionCode)
        versionName(Environments.Release.appVersionName)

        testInstrumentationRunner(Config.testRunner)

        // Configs
        buildConfigField("String", "BASE_URL", "\"" + Environments.Release.baseUrl + "\"")

    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
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
    lintOptions {
        this.isCheckReleaseBuilds =true
        // Or, if you prefer, you can continue to check for errors in release builds,
        // but continue the build even when errors are found:
        this.isAbortOnError =false
        this.disable("ContentDescription")

    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // Modules
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.cache))
    implementation(project(Modules.presentation))

    // Core Dependencies
    implementation(UiDep.kotlin)
    implementation(UiDep.coreKtx)
    implementation(UiDep.appCompat)
    implementation(UiDep.material)
    implementation(UiDep.constraint)
    implementation(UiDep.activityKtx)
    implementation(UiDep.navigationFragmentKtx)

    // LifeCycle
    UiDep.LifeCycle.forEach {
        implementation(it)
    }
    // Dagger-Hilt
    UiDep.DaggerHilt.forEach {
        implementation(it)
    }

    UiDep.DaggerHiltKapt.forEach {
        kapt(it)
    }
    // Coroutines
    UiDep.Coroutines.forEach {
        implementation(it)
    }
    implementation(UiDep.kotlinBinding)

    // Timber
    implementation(UiDep.timber)




    // Test Dependencies
    testImplementation(UiDep.Test.junit)
    testImplementation(UiDep.Test.assertJ)
    testImplementation(UiDep.Test.mockitoKotlin)
    testImplementation(UiDep.Test.mockitoInline)
    testImplementation(UiDep.Test.coroutines)
    testImplementation(UiDep.Test.androidxArchCore)
    testImplementation(UiDep.Test.robolectric)
    testImplementation(UiDep.Test.testExtJunit)
    testImplementation(UiDep.Test.espressoCore)
    testImplementation(UiDep.Test.espressoContrib)
    testImplementation(UiDep.Test.espressoIntents)
    testImplementation(UiDep.Test.testRunner)


}