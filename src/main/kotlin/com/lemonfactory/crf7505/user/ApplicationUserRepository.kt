package com.lemonfactory.crf7505.user

import com.googlecode.objectify.Key
import com.googlecode.objectify.ObjectifyService
import com.googlecode.objectify.ObjectifyService.ofy

class ApplicationUserRepository {

    fun save(user: ApplicationUser) {
        ObjectifyService.run {
            ofy().save().entity(user).now()
        }
    }

    fun findAll(): List<ApplicationUser> {
        return ObjectifyService.run {
            ofy().load().type(ApplicationUser::class.java).list()
        }
    }

    fun findByUsername(username: String): ApplicationUser? {
        return ObjectifyService.run {
            ofy().load().type(ApplicationUser::class.java).id(username).now()
        }
    }

    fun deleteUser(userKey: Key<ApplicationUser>) {
        ObjectifyService.run {
            ofy().delete().key(userKey)
        }
    }


}