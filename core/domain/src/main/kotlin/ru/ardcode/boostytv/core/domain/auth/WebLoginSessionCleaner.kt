package ru.ardcode.boostytv.core.domain.auth

fun interface WebLoginSessionCleaner {
    suspend fun clear()
}
