package com.lemonfactory.crf7505.controllers

import com.lemonfactory.crf7505.user.ApplicationUser
import com.lemonfactory.crf7505.user.ApplicationUserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController(val userRepository: ApplicationUserRepository,
                     val bCryptPasswordEncoder: BCryptPasswordEncoder) {




    @GetMapping("")
    fun user(): String {
        val user = ApplicationUser("mattcitron2", "Matthieu", "password")
        userRepository.save(user)
        return userRepository.findAll().toString()
    }

    @PostMapping("/sign-up")
    fun registerUser(@RequestBody user: ApplicationUser): String {
        val hashedPassword = bCryptPasswordEncoder.encode(user.password)
        val userToStore = ApplicationUser(user.username, user.firstname, hashedPassword)
        userRepository.save(userToStore)
        return "Welcome ${userToStore.firstname} !"
    }

}