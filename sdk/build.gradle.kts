plugins {
    java
    kotlin("jvm")
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/imulab/connect-sdk")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

