plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.5.31"
}

group = "com.cm.nuntius.lib"
version = "1.0-SNAPSHOT"

dependencies {
    api("io.ktor:ktor-client-core:1.6.7")
    api("io.ktor:ktor-client-cio:1.6.7")
    api("io.ktor:ktor-client-serialization:1.6.7")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.16")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
}
