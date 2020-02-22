package tech.comfortheart.uaa.tech.comfortheart.uaa.service

import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import javax.inject.Singleton

/**
 * Description: A Redis Lock Helper
 * User: Samuel Chan
 * Date: 2020-02-04
 * Time: 17:39
 */


const val LOCK_PREFIX = "redis_lock"
const val LOCK_EXPIRE = 1000; // ms

@Singleton
class RedisLockHelper {

    private val commands: RedisCommands<String, String>

    constructor(connection: StatefulRedisConnection<String, String>) {
        commands = connection.sync()
    }

    /**
     *  Acquire a lock.
     *
     * @param key
     * @return got the lock or not
     */
    fun lock(key: String): Boolean {
        val lock = LOCK_PREFIX + key;


        val expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
        val acquire = commands.setnx(lock, expireAt.toString());

        if (acquire) {
            return true;
        } else {

            val value = commands.get(lock)

            if (value != null && value.isNotEmpty()) {

                val expireTime = value.toLong()

                if (expireTime < System.currentTimeMillis()) {
                    // in case the lock is expired
                    val oldValue = commands.getset(lock, (System.currentTimeMillis() + LOCK_EXPIRE + 1).toString());
                    // avoid dead lock
                    return oldValue.toLong() < System.currentTimeMillis()
                }
            }
        }
        return false
    }

    /**
     * Delete the lock
     *
     * @param key
     */
    fun delete(key: String) {
        commands.del(key)
    }

}