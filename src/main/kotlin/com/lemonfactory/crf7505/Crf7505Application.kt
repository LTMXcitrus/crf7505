package com.lemonfactory.crf7505

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer


@SpringBootApplication
class Crf7505Application: SpringBootServletInitializer()

fun main(args: Array<String>) {
    runApplication<Crf7505Application>(*args)
}


