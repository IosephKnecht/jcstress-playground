import com.github.erizo.gradle.JcstressTask

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.github.reyerizo.gradle.jcstress") version "0.8.15"
    kotlin("kapt") version "1.9.0"
}

group = "io.github.iosephknecht"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("io.github.reyerizo.gradle.jcstress:io.github.reyerizo.gradle.jcstress.gradle.plugin:0.8.15")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

tasks.named<JcstressTask>("jcstress") {
//    this.args("-l", "true")
    this.jcstressTestName = "FixedBufferHandlerThreadHolderTest"
}

jcstress {
    verbose = "true"
    iterations = "2"
    forks = "1"
    heapPerFork = "4096"
    affinityMode = "NONE"
}