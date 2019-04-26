import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/imulab/connect-sdk")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0")
    implementation(project(":code-hybrid-flow-sdk"))
    implementation("io.imulab:connect-sdk:0.1.1")
    implementation("org.koin:koin-core:2.0.0-rc-2")
    implementation("io.grpc:grpc-stub:1.15.1")
    implementation("io.grpc:grpc-netty:1.15.1")
    implementation("com.uchuhimo:konf:0.13.2")
    implementation("redis.clients:jedis:3.0.1")
    implementation("com.rabbitmq:amqp-client:5.7.0")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("ch.qos.logback:logback-classic:1.1.7")

    testImplementation("org.koin:koin-test:2.0.0-rc-2")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
}

application {
    mainClassName = "io.imulab.q.flow.App"
}

tasks {
    test {
        useJUnitPlatform()
    }
}