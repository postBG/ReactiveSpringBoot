package com.example.reactive.user.controller

import com.example.reactive.user.models.User
import com.example.reactive.user.repositories.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Configuration
class UserRoutingConfig {
    @Bean
    fun routes(userHandler: UserHandler): RouterFunction<ServerResponse> {
        return route(GET("/users"), HandlerFunction<ServerResponse>(userHandler::findAll))
    }
}

@Component
class UserHandler(val userRepository: UserRepository) {
    fun findAll(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(this.userRepository.findAll(), User::class.java)
    }
}