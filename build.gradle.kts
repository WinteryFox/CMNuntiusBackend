import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.springframework.boot") version "2.5.4" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("plugin.spring") version "1.5.21" apply false
    kotlin("jvm") version "1.5.31"
}

allprojects {
    version = "1.0.0"

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf("-Xjsr305=strict", "-Xopt-in=kotlin.RequiresOptIn")
            jvmTarget = "16"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
