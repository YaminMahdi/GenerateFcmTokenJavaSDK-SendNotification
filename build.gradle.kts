plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.fcm"
version = "1.0-SNAPSHOT"
val ktor= "3.0.0-beta-1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor")
    implementation("io.ktor:ktor-client-cio:$ktor")
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.19.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}