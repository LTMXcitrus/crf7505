package com.lemonfactory.crf7505.repository

import com.googlecode.objectify.Key
import com.googlecode.objectify.ObjectifyService
import com.lemonfactory.crf7505.model.ObjectifyElement

class ObjectifyDAO<T : ObjectifyElement> {

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
        try {
            return ObjectifyService.run {
                ObjectifyService.ofy().load().type(T::class.java).id(id).now()
            }
        } catch(e: IllegalStateException) {
            return null
        }
    }

    inline fun <reified T: ObjectifyElement> delete(element: T): Boolean {
        val key = Key.create(T::class.java, element.getId())
        ObjectifyService.run {
            ObjectifyService.ofy().delete().key(key)
        }
        return true
    }
}