apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUi))
    "implementation" (Compose.permission)
    "implementation"(project(Modules.les1Domain))
    "implementation"(Google.mapsCompose)
    "implementation"(Google.maps)


    "implementation"("com.google.android.gms:play-services-location:19.0.1")
}