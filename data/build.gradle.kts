import dependencies.DataDep

plugins {
    id(Config.Plugins.kotlin)
    id(Config.Plugins.javaLibrary)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    // Modules
    implementation(project(Modules.domain))

    implementation(project(Modules.cache))


    // Kotlin
    implementation(DataDep.kotlin)
    // Coroutines
    implementation(DataDep.coroutineCore)
    implementation(DataDep.coroutineCoreJvm)
    // JavaX
    implementation(DataDep.javax)

    // Gson parse
    implementation(DataDep.gson)


    // Kotlin



    // DataDep (Retrofit, OkHttp, Interceptor, Moshi)
    DataDep.retrofit.forEach { implementation(it) }

    // Test Dependencies
    testImplementation(DataDep.Test.junit)
    testImplementation(DataDep.Test.assertJ)
    testImplementation(DataDep.Test.mockitoKotlin)
    testImplementation(DataDep.Test.mockitoInline)
    testImplementation(DataDep.Test.coroutines)
}
