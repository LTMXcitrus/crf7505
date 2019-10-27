package com.lemonfactory.crf7505.utils

import com.nhaarman.mockito_kotlin.KArgumentCaptor
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

fun <T> any(): T {
    Mockito.any<T>()
    return uninitialized()
}

inline fun <reified T: Any> mock() = Mockito.mock(T::class.java)

inline fun <reified A: Any,reified B: Any> argumentCaptorPair(): Pair<KArgumentCaptor<A>, KArgumentCaptor<B>> {
    return Pair(KArgumentCaptor(ArgumentCaptor.forClass(A::class.java), A::class),
            KArgumentCaptor(ArgumentCaptor.forClass(B::class.java), B::class))
}

inline fun <reified A: Any,reified B: Any, reified C: Any> argumentCaptorTriple(): Triple<KArgumentCaptor<A>, KArgumentCaptor<B>, KArgumentCaptor<C>> {
    return Triple(
            KArgumentCaptor(ArgumentCaptor.forClass(A::class.java), A::class),
            KArgumentCaptor(ArgumentCaptor.forClass(B::class.java), B::class),
            KArgumentCaptor(ArgumentCaptor.forClass(C::class.java), C::class))
}

fun <T> uninitialized(): T = null as T