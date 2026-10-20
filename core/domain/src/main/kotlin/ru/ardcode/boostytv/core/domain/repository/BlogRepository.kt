package ru.ardcode.boostytv.core.domain.repository

import ru.ardcode.boostytv.core.model.Blog
import ru.ardcode.boostytv.core.model.Page
import ru.ardcode.boostytv.core.model.Post

interface BlogRepository {
    suspend fun blog(blogUrl: String): Blog
    suspend fun post(blogUrl: String, postId: String): Post
    suspend fun postsPage(blogUrl: String, cursor: String?): Page<Post>
    suspend fun recentPosts(blogUrl: String, limit: Int = 12): List<Post>
}
