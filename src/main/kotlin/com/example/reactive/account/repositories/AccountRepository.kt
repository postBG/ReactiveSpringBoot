package com.example.reactive.account.repositories

import com.example.reactive.account.models.Account
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import java.util.*

interface AccountRepository: ReactiveCassandraRepository<Account, UUID>