object Versions {
    //Essentials
    const val coreKtx = "1.8.0"
    const val material = "1.6.1"
    const val appCompat = "1.5.0"
    const val constraintLayout = "2.1.4"

    //ViewModel + LiveData
    const val lifecycle = "2.5.1"

    //Room
    const val room = "2.4.3"

    //Koin
    const val koin = "3.2.0"
}

object Libs {
    //Essentials
    const val core_ktx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    //ViewModel + LiveData
    const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    //Room
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"

    //Koin
    const val koin_core = "io.insert-koin:koin-core:${Versions.koin}"
    const val koin_android = "io.insert-koin:koin-core:${Versions.koin}"
    const val koin_compat = "io.insert-koin:koin-core:${Versions.koin}"
}