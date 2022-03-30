apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.les2Domain))
    "implementation"(project(Modules.coreUi))
    "implementation" (Compose.paging)
}