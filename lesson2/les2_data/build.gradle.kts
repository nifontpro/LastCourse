apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation" (project(Modules.les2Domain))

    "implementation" (Kotlin.serialization)
    "implementation" (Retrofit.retrofit)
    "implementation" (Retrofit.kotlinxConverter)

    "implementation" (Room.roomRuntime)
    "kapt" (Room.roomCompiler)
    "implementation" (Room.roomKtx)
    "implementation" (Room.paging)

    "implementation" (Compose.paging)
}
