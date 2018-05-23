package com.example.reactive.user.models

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table
class User(@PrimaryKey val uid: UUID,
           @Column val email: String,
           @Column val password: String) {
}