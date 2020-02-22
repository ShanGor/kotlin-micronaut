package tech.comfortheart.uaa.tech.comfortheart.uaa.domain

import java.util.*
import javax.persistence.*

@Entity
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BIGINT")
    var id: Long? = -1,
    @Column(columnDefinition = "VARCHAR(30)")
    var name: String? = null,
    @Column(columnDefinition = "VARCHAR(128)")
    var password: String? = null,
    var lastUpdateDate: Date = Date()
    )