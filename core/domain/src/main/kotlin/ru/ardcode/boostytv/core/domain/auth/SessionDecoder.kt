package ru.ardcode.boostytv.core.domain.auth

import ru.ardcode.boostytv.core.model.Session

fun interface SessionDecoder {
    fun decode(rawAuth: String, clientId: String): Session
}
