package com.lemonfactory.crf7505.controllers

import com.lemonfactory.crf7505.user.User
import com.lemonfactory.crf7505.user.UserService
import com.lemonfactory.crf7505.user.encrypt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController {


    @GetMapping("/user")
    fun user(): String {
        val user = User("mattcitron2", "Matthieu", "password")
        UserService.save(user)
        return UserService.findAll().toString()
    }

    @PostMapping("/user")
    fun registerUser(@RequestBody user: User): String {
        val hashedPassword = encrypt(user.password)
        val userToStore = User(user.username, user.firstname, hashedPassword)
        UserService.save(userToStore)
        return "Welcome ${userToStore.firstname} !"
    }

}