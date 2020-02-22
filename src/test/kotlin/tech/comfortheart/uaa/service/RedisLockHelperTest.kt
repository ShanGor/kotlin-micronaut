package tech.comfortheart.uaa.service

import io.kotlintest.specs.StringSpec
import io.micronaut.test.annotation.MicronautTest
import kotlinx.coroutines.*
import tech.comfortheart.uaa.tech.comfortheart.uaa.service.RedisLockHelper

@MicronautTest
class RedisLockHelperTest(redisLockHelper: RedisLockHelper): StringSpec( {

    "test lock" {
        val cores = Runtime.getRuntime().availableProcessors()
        println("number of cores: $cores")

        suspend fun testLock(key: String, threadId: String) {
            val lock = redisLockHelper.lock(key)
            if (lock) {
                println("Successfully got lock - $threadId")
                delay(2000)
                redisLockHelper.delete(key)
            } else {
                println("Failed to obtain lock - $threadId")
            }
        }

        val totalThreads = 10000

        val jobs = List(totalThreads) {
            val threadId = it.toString()
            launch(Dispatchers.Default) {
                try {
                    testLock("hey", threadId)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

        jobs.forEach { it.join() }
        // After all thread done, acquire again, expect to be successful.
        // After all thread done, acquire again, expect to be successful.
        testLock("hey", "final success")
    }
})


