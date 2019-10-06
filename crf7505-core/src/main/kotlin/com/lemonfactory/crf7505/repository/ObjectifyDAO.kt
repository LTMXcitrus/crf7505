package com.lemonfactory.crf7505.repository

import com.googlecode.objectify.Key
import com.googlecode.objectify.ObjectifyService

class ObjectifyDAO<T> {
    fun save(thing: T) {
        ObjectifyService.run {
            ObjectifyService.ofy().save().entity(thing).now()
        }
    }

    inline fun <reified T> findAll(): List<T> {
        return ObjectifyService.run {
            ObjectifyService.ofy().load().type(T::class.java).list()
        }
    }

    inline fun <reified T> findById(id: String): T? {
        return ObjectifyService.run {
            ObjectifyService.ofy().load().type(T::class.java).id(id).now()
        }
    }

    fun delete(thingKey: Key<T>) {
        ObjectifyService.run {
            ObjectifyService.ofy().delete().key(thingKey)
        }
    }
}