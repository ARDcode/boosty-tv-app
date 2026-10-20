package ru.ardcode.boostytv.core.data.repository

import ru.ardcode.boostytv.core.domain.repository.BlogRepository
import ru.ardcode.boostytv.core.model.Blog
import ru.ardcode.boostytv.core.model.Page
import ru.ardcode.boostytv.core.model.Post
import ru.ardcode.boostytv.core.network.BoostyApi
import ru.ardcode.boostytv.core.network.mapper.toDomain
import ru.ardcode.boostytv.core.network.mapper.toPostPage
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultBlogRepository @Inject constructor(
    private val api: BoostyApi,
    private val json: Json,
) : BlogRepository {
    override suspend fun blog(blogUrl: String): Blog = api.getBlog(blogUrl).toDomain()

    override suspend fun post(blogUrl: String, postId: String): Post =
        api.getPost(blogUrl, postId).toDomain(json, fallbackBlogUrl = blogUrl)

    override suspend fun postsPage(blogUrl: String, cursor: String?): Page<Post> =
        videoPostPage(cursor) { next ->
            api.getBlogPosts(blogUrl, offset = next).toPostPage(json, blogUrl)
        }

    override suspend fun recentPosts(blogUrl: String, limit: Int): List<Post> =
        videoPostPage(null) { cursor ->
            api.getBlogPosts(blogUrl, limit = limit, offset = cursor).toPostPage(json, blogUrl)
        }.items
}
