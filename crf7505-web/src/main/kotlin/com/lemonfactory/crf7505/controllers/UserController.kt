package com.lemonfactory.crf7505.controllers

import com.lemonfactory.crf7505.repository.ObjectifyDAO
import com.lemonfactory.crf7505.user.ApplicationUser
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController(val userRepository: ObjectifyDAO<ApplicationUser>,
                     val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    @PostMapping("/sign-up")
    fun registerUser(@RequestBody user: ApplicationUser): String {
        val hashedPassword = bCryptPasswordEncoder.encode(user.password)
        val userToStore = user.copy(password = hashedPassword)
        userRepository.save(userToStore)
        return "Welcome ${userToStore.firstname} !"
    }

}
