package io.imulab.q.flow

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.ConfigSpec
import java.time.Duration

object RedisSpec : ConfigSpec("redis") {
    val host by optional("127.0.0.1")
    val port by optional(6379)
}

object RabbitSpec : ConfigSpec("rabbit") {
    val host by optional("127.0.0.1")
    val port by optional(5672)
}

object ConnectSpec : ConfigSpec("connect") {
    val codeLifespan by required<Duration>("codeLifespan")
}

fun main() {
    val config = Config {
        addSpec(RedisSpec)
        addSpec(ConnectSpec)
    }
        .from.yaml.resource("application.yaml")
        .from.env()
        .from.systemProperties()

}