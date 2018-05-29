package com.example.reactive.configs

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.factory.PasswordEncoderFactories

@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.authorizeExchange()
                .anyExchange().permitAll()
                .and()
                .httpBasic().and()
                .build()
    }

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val userBuilder = User.builder()
        val passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

        val rob = userBuilder
                .username("postBG")
                .password(passwordEncoder.encode("postBG1234"))
                .roles("USER")
                .build()

        val admin = userBuilder
                .username("admin")
                .password(passwordEncoder.encode("admin1234"))
                .roles("USER", "ADMIN")
                .build()
        return MapReactiveUserDetailsService(rob, admin)
    }
}
