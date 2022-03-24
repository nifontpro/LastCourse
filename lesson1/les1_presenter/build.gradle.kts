apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.les1Domain))
    "implementation"(Google.mapsCompose)
    "implementation"(Google.maps)
}