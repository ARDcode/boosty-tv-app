package ru.ardcode.boostytv.core.domain.repository

import ru.ardcode.boostytv.core.model.Page
import ru.ardcode.boostytv.core.model.Post
import ru.ardcode.boostytv.core.model.Subscription

interface FeedRepository {
    suspend fun feedPage(cursor: String?): Page<Post>
    suspend fun subscriptions(limit: Int = 100): List<Subscription>
}
