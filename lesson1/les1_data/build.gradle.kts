apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation" (project(Modules.les1Domain))

    "implementation" (Room.roomRuntime)
    "kapt" (Room.roomCompiler)
    "implementation" (Room.roomKtx)

}
