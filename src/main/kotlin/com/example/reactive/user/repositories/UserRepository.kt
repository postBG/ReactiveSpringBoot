package com.example.reactive.user.repositories

import com.example.reactive.user.models.User
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import reactor.core.publisher.Flux
import java.util.*

interface UserRepository: ReactiveCassandraRepository<User, UUID> {
    fun findByEmail(email: String): Flux<User>
}