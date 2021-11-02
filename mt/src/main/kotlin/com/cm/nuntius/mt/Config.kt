package com.cm.nuntius.mt

import com.cm.nuntius.lib.MessagingClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebFluxSecurity
class Config {
    @Bean
    fun messagingClient() = MessagingClient(System.getenv("CM_TOKEN"))

    @Bean
    fun configure(httpSecurity: ServerHttpSecurity): SecurityWebFilterChain =
        httpSecurity
            .formLogin().disable()
            .cors().configurationSource {
                CorsConfiguration().apply {
                    allowedOriginPatterns = listOf("*")
                    allowCredentials = true
                    allowedMethods = listOf("*")
                    allowedHeaders = listOf("*")
                }
            }.and() // TODO: Enable this again in the future
            .csrf().disable()
            .httpBasic().disable()
            .authorizeExchange().anyExchange().permitAll().and()
            .build()
}
