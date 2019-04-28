import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
    id("jacoco")
    java
    id("com.github.johnrengelman.shadow").version("5.0.0")
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/imulab/connect-sdk")
    mavenCentral()
}

jacoco {
    toolVersion = "0.8.2"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0")
    implementation(project(":code-hybrid-flow-sdk"))
    implementation("io.imulab:connect-sdk:0.1.1")
    implementation("io.grpc:grpc-protobuf:1.15.1")
    implementation("io.grpc:grpc-stub:1.15.1")
    implementation("io.grpc:grpc-netty:1.15.1")
    implementation("io.grpc:grpc-services:1.15.1")
    implementation("com.uchuhimo:konf:0.13.2")
    implementation("redis.clients:jedis:3.0.1")
    implementation("com.rabbitmq:amqp-client:5.7.0")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("ch.qos.logback:logback-classic:1.1.7")

    testImplementation("org.koin:koin-test:2.0.0-rc-2")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
}

application {
    mainClassName = "io.imulab.q.flow.AppKt"
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
        }
    }
    test {
        useJUnitPlatform()
        testLogging {
            events("PASSED", "FAILED", "SKIPPED")
        }
    }
    jacocoTestReport {
        reports {
            html.apply {
                isEnabled = true
            }
            xml.apply {
                isEnabled = true
            }
        }
    }
    shadowJar {
        archiveBaseName.set("code-hybrid-flow-service")
        archiveClassifier.set("fat")
        archiveVersion.set(project.version.toString())
        mergeServiceFiles()
    }
}