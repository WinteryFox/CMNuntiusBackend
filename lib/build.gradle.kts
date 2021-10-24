plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.5.31"
}

group = "com.cm.nuntius.lib"
version = "1.0-SNAPSHOT"

dependencies {
    api("io.ktor:ktor-client-core:1.6.4")
    api("io.ktor:ktor-client-cio:1.6.4")
    api("io.ktor:ktor-client-serialization:1.6.4")
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
}
