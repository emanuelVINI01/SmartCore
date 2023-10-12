import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    id("com.github.johnrengelman.shadow") version "4.0.4"
}

group = "com.emanuelvini.smart"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":bukkit"))
    implementation(project(":bungee"))
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}