package com.cm.nuntius.mo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class Config {
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
