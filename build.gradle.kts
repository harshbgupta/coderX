import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
}

group = "com.vertical"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //test
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.0") // Kotlin test library

    implementation(kotlin("stdlib-jdk8"))//kotlin
    implementation("com.google.code.gson:gson:2.10.1")//GSON
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")//Coroutine
}

tasks.test {
    useJUnitPlatform()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "17"
}