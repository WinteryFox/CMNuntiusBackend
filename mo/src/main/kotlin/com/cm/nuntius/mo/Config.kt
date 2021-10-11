package com.cm.nuntius.mo

import com.cmtelecom.text.sdk.MessagingClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class Config {
    @Bean
    fun messagingClient() = MessagingClient(System.getenv("CM_TOKEN"))

    @Bean
    fun configure(httpSecurity: ServerHttpSecurity): SecurityWebFilterChain =
        httpSecurity
            .formLogin().disable()
            .cors().disable()
            .csrf().disable()
            .httpBasic().disable()
            .authorizeExchange().anyExchange().permitAll().and()
            .build()
}
