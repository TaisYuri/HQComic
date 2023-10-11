package com.example.hqmarvel.data

class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            return null
        } else {
            hasBeenHandled = true
            content
        }
    }
}