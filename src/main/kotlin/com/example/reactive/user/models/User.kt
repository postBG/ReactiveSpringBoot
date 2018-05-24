package com.example.reactive.user.models

import com.datastax.driver.core.utils.UUIDs
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table
data class User(@PrimaryKey val uid: UUID,
                @Column val email: String,
                @Column val password: String) {
    @Column
    val userName: String = email.split("@").first()

    constructor(email: String, password: String) : this(UUIDs.timeBased(), email, password)
}