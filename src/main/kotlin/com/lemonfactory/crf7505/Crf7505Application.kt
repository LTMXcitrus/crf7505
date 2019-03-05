package com.lemonfactory.crf7505

import com.googlecode.objectify.ObjectifyService
import com.lemonfactory.crf7505.user.User
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer


@SpringBootApplication
class Crf7505Application : SpringBootServletInitializer() {
    init {
        ObjectifyService.init()
        ObjectifyService.register(User::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<Crf7505Application>(*args)
}


