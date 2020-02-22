package tech.comfortheart.uaa

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages(Application.javaClass.packageName)
                .mainClass(Application.javaClass)
                .start()
    }
}