package com.example.reactive.user.controller

import com.example.reactive.user.models.User
import com.example.reactive.user.repositories.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
@RequestMapping("/users")
class UserController(val userRepository: UserRepository) {
    @GetMapping
    fun findAll(): Flux<User> {
        return userRepository.findAll()
    }
}