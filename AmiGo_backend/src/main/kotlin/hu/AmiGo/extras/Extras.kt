package com.AmiGo.extras

import java.util.*

fun <T> Optional<T>.toNullable(): T? {
    return if (this.isPresent) return this.get() else null
}