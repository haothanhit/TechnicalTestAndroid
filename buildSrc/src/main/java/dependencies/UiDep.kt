package dependencies

object UiDep {

    // Kotlin
    const val kotlin = Dependencies.KotlinDep.kotlin

    // Core
    const val coreKtx = Dependencies.CoreDep.coreKtx
    const val appCompat = Dependencies.CoreDep.appCompat
    const val material = Dependencies.CoreDep.material
    const val constraint = Dependencies.CoreDep.constraint
    const val activityKtx = Dependencies.CoreDep.activityKtx
    const val navigationFragmentKtx = Dependencies.CoreDep.navigationFragmentKtx


    // LifeCycle
    val LifeCycle = listOf(
        Dependencies.LifeCycleDep.viewModelKtx,
        Dependencies.LifeCycleDep.lifeCycleExtension,
        Dependencies.LifeCycleDep.lifeCycleRuntime,
        Dependencies.LifeCycleDep.lifeCycleRuntimeKtx
    )

    // Hilt
    val DaggerHilt = listOf(
        Dependencies.DaggerHiltDep.hiltAndroid
    )

    // Hilt
    const val kotlinBinding = Dependencies.kotlinBindingCompiler



    val DaggerHiltKapt = listOf(
        Dependencies.DaggerHiltDep.hiltAndroidKapt,
        Dependencies.DaggerHiltDep.hiltKapt
    )

    // Coroutines
    val Coroutines = listOf(
        Dependencies.CoroutinesDep.coroutineCore,
        Dependencies.CoroutinesDep.coroutineAndroid,
        Dependencies.CoroutinesDep.coroutineCoreJvm
    )

    const val timber = Dependencies.TimberDep.timber





    object Test {
        const val junit = Dependencies.TestDep.junit
        const val coroutines = Dependencies.TestDep.coroutinesTest
        const val mockitoKotlin = Dependencies.TestDep.mockitoKotlin
        const val mockitoInline = Dependencies.TestDep.mockitoInline
        const val assertJ = Dependencies.TestDep.assertJ
        const val androidxArchCore = Dependencies.TestDep.androidxArchCore
        const val robolectric = Dependencies.TestDep.robolectric
        const val testExtJunit = Dependencies.TestDep.testExtJunit
        const val espressoCore = Dependencies.TestDep.espressoCore
        const val espressoContrib = Dependencies.TestDep.espressoContrib
        const val espressoIntents = Dependencies.TestDep.espressoIntents
        const val testRunner = Dependencies.TestDep.testRunner




    }
}