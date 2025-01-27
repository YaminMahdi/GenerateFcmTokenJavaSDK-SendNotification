plugins {
    kotlin("jvm") version "2.1.0"
}

group = "org.fcm"
version = "1.0-SNAPSHOT"
val ktor= "3.0.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor")
    implementation("io.ktor:ktor-client-cio:$ktor")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.19.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}