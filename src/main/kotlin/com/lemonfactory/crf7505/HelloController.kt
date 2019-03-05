package com.lemonfactory.crf7505

import com.lemonfactory.crf7505.user.User
import com.lemonfactory.crf7505.user.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/")
    fun hello(): String {
        return "Hello Spring Boot!"
    }

    @GetMapping("/user")
    fun user(): String {
        val user = User("mattcitron2", "Matthieu", "password")
        UserService.save(user)
        return UserService.findAll().toString()
    }

}