package com.example.reactive.account.controller

import com.example.reactive.account.models.Account
import com.example.reactive.account.repositories.AccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.*
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.*

@Configuration
class AccountRoutingConfig {
    @Bean
    fun routes(accountHandler: AccountHandler): RouterFunction<ServerResponse> {
        return route(GET("/accounts"), HandlerFunction<ServerResponse>(accountHandler::findAll))
                .andRoute(POST("/accounts"), HandlerFunction<ServerResponse>(accountHandler::save))
                .andRoute(GET("/accounts/{id}"), HandlerFunction<ServerResponse>(accountHandler::findById))
    }
}

@Component
class AccountHandler(val accountRepository: AccountRepository) {
    fun findAll(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(this.accountRepository.findAll(), Account::class.java)
    }

    fun save(request: ServerRequest): Mono<ServerResponse> {
        return request.formData().flatMap { formData ->
            val email = formData.getFirst("email")
            val password = formData.getFirst("password")

            if (email.isNullOrBlank() or password.isNullOrBlank()) {
                ServerResponse.badRequest().body(fromObject("email or password is empty"))
            } else {
                this.accountRepository.save(Account(email!!, password!!))
                        .flatMap { ServerResponse.noContent().build() }
                        .switchIfEmpty(ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
            }
        }
    }

    fun findById(request: ServerRequest): Mono<ServerResponse> {
        return this.accountRepository.findById(UUID.fromString(request.pathVariable("id")))
                .flatMap { user -> ServerResponse.ok().body(Mono.just(user), Account::class.java) }
                .switchIfEmpty(ServerResponse.notFound().build())
    }
}