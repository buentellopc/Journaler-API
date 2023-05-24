package com.bpc.journalerapi.security

import com.bpc.journalerapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import java.util.*


@Configuration
@EnableWebSecurity
class WebSecurityConfiguration() {

    @Autowired
    lateinit var service: UserService

    /**
     * Will be resolved into: WebSecurityEntryPoint injected instance.
     */
    @Autowired
    lateinit var unauthorizedHandler: AuthenticationEntryPoint

    @Autowired
    lateinit var successHandler: WebSecurityAuthSuccessHandler

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(
        http: HttpSecurity,
        bCryptPasswordEncoder: BCryptPasswordEncoder,
        userDetailService: UserService?
    ): AuthenticationManager? {
        return http.getSharedObject<AuthenticationManagerBuilder>(AuthenticationManagerBuilder::class.java)
            .userDetailsService<UserDetailsService>(userDetailService)
            .passwordEncoder(bCryptPasswordEncoder)
            .and()
            .build()
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http?.csrf()?.disable()
            ?.exceptionHandling()
            ?.authenticationEntryPoint(unauthorizedHandler)
            ?.and()
            ?.authorizeRequests()
            /**
             * Access to Notes and Todos API calls is given to any authenticated system user.
             */
            ?.antMatchers("/notes")?.authenticated()
            ?.antMatchers("/notes/**")?.authenticated()
            ?.antMatchers("/todos")?.authenticated()
            ?.antMatchers("/todos/**")?.authenticated()
            /**
             * Access to User API calls is given only to Admin user.
             */
            ?.antMatchers("/users")?.hasAnyAuthority("ADMIN")
            ?.antMatchers("/users/**")?.hasAnyAuthority("ADMIN")
            ?.and()
            ?.formLogin()
            ?.successHandler(successHandler)
            ?.failureHandler(SimpleUrlAuthenticationFailureHandler())
            ?.and()
            ?.logout()
        return http.build()
    }


    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }

}