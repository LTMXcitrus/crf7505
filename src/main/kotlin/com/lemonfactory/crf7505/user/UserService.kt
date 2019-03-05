package com.lemonfactory.crf7505.user

import com.googlecode.objectify.Key
import com.googlecode.objectify.ObjectifyService
import com.googlecode.objectify.ObjectifyService.ofy

object UserService {

    fun save(user: User) {
        ObjectifyService.run {
            ofy().save().entity(user).now()
        }
    }

    fun findAll(): List<User> {
        return ofy().load().type(User::class.java).list()
    }

    fun findUser(username: String): User? {
        return ofy().load().type(User::class.java).filter("username", username).first().now()
    }

    fun deleteUser(userKey: Key<User>) {
        ObjectifyService.run {
            ofy().delete().key(userKey)
        }
    }


}