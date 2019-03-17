package com.lemonfactory.crf7505

import com.googlecode.objectify.ObjectifyService
import com.lemonfactory.crf7505.infrastructure.security.user.ApplicationUser
import com.lemonfactory.crf7505.infrastructure.security.user.ApplicationUserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import com.googlecode.objectify.ObjectifyFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.PropertySource


@SpringBootApplication
@PropertySource("classpath:application.properties")
class Crf7505Application : SpringBootServletInitializer() {
    init {
        ObjectifyService.init()
        ObjectifyService.register(ApplicationUser::class.java)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userRepository(): ApplicationUserRepository {
        return ApplicationUserRepository()
    }

    @Bean
    fun objectifyFilter(): FilterRegistrationBean<*> {
        val registration = FilterRegistrationBean<ObjectifyFilter>()
        registration.filter = ObjectifyFilter()
        registration.addUrlPatterns("/*")
        registration.order = 1

        return registration
    }
}

fun main(args: Array<String>) {
    runApplication<Crf7505Application>(*args)
}


