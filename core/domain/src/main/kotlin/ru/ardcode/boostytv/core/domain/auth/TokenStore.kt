package ru.ardcode.boostytv.core.domain.auth

import ru.ardcode.boostytv.core.model.Session
import kotlinx.coroutines.flow.Flow

/** Persistence for the one [Session] this app holds. */
interface TokenStore {
    val session: Flow<Session?>

    /** Latest value without collecting; null when signed out. */
    suspend fun peek(): Session?

    /**
     * Must be durable before it returns. The refresh token rotates, so a save
     * that silently fails costs the user their session.
     */
    suspend fun save(session: Session)

    suspend fun clear()
}
