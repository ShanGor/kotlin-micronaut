package tech.comfortheart.uaa.tech.comfortheart.uaa.repository

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import tech.comfortheart.uaa.tech.comfortheart.uaa.domain.User
import io.micronaut.data.repository.CrudRepository


@JdbcRepository(dialect = Dialect.POSTGRES)
interface UserRepo: CrudRepository<User, Long> {
    fun findByName(name: String): List<User>
}