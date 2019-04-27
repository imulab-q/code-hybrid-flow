import com.google.protobuf.gradle.*

plugins {
    idea
    java
    kotlin("jvm")
    id("com.google.protobuf") version "0.8.8"
    maven
    `maven-publish`
}

val sourceJar by tasks.registering(Jar::class) {
    classifier = "sources"
    from(sourceSets.main.get().allSource)
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/imulab/connect-sdk")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0")
    api("io.imulab:connect-sdk:0.1.1")
    implementation("com.google.protobuf:protobuf-java:3.6.1")
    implementation("io.grpc:grpc-stub:1.15.1")
    implementation("io.grpc:grpc-protobuf:1.15.1")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
}

publishing {
    repositories {
        maven {
            name = "release"
            url = uri("s3://maven.imulab/releases")
            credentials(AwsCredentials::class.java) {
                accessKey = System.getenv("AWS_ACCESS_KEY_ID")
                secretKey = System.getenv("AWS_SECRET_ACCESS_KEY")
            }
        }
        maven {
            name = "snapshot"
            url = uri("s3://maven.imulab/snapshots")
            credentials(AwsCredentials::class.java) {
                accessKey = System.getenv("AWS_ACCESS_KEY_ID")
                secretKey = System.getenv("AWS_SECRET_ACCESS_KEY")
            }
        }
    }
    publications {
        create<MavenPublication>("sdk") {
            from(components["java"])
            artifact(sourceJar.get())
            artifactId = "code-hybrid-flow-sdk"
            version = project.version.toString()
        }
    }
}

tasks {
    protobuf {
        protoc {
            artifact = "com.google.protobuf:protoc:3.6.1"
        }
        plugins {
            id("grpc") {
                artifact = "io.grpc:protoc-gen-grpc-java:1.15.1"
            }
        }
        generateProtoTasks {
            ofSourceSet("main").forEach {
                it.plugins {
                    id("grpc")
                }
            }
        }
        generatedFilesBaseDir = "$projectDir/src/generated"
    }
}