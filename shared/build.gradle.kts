import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
}

group = "com.emanuelvini.smart"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}