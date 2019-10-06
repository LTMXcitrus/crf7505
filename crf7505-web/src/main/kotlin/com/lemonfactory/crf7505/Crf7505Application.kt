package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.security.user.ApplicationUser
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import com.googlecode.objectify.ObjectifyFilter
import com.googlecode.objectify.ObjectifyService
import com.lemonfactory.appenginemailclient.MailClientModule
import com.lemonfactory.crf7505.domain.model.Volunteer
import com.lemonfactory.crf7505.repository.ObjectifyDAO
import com.lemonfactory.pegass.client.PegassModule
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource


@SpringBootApplication
@Import(CrfModule::class,
        PegassModule::class,
        MailClientModule::class)
@PropertySource("classpath:application.properties")
class Crf7505Application : SpringBootServletInitializer() {
    init {
        ObjectifyService.init()
        ObjectifyService.register(ApplicationUser::class.java)
        ObjectifyService.register(Volunteer::class.java)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userRepository(): ObjectifyDAO<ApplicationUser> {
        return ObjectifyDAO()
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


