package ru.ardcode.boostytv.core.data.repository

import ru.ardcode.boostytv.core.model.Page
import ru.ardcode.boostytv.core.model.Post

/** Skip text-only pages without losing Boosty's opaque continuation cursor. */
internal suspend fun videoPostPage(
    cursor: String?,
    fetch: suspend (String?) -> Page<Post>,
): Page<Post> {
    var next = cursor
    val visited = mutableSetOf(cursor)
    while (true) {
        val page = fetch(next)
        check(page.isLast || page.nextCursor == null || page.nextCursor !in visited) {
            "Boosty returned a repeated posts cursor"
        }
        val videos = page.items.filter { it.hasVideo }
        if (videos.isNotEmpty() || page.isLast || page.nextCursor == null) {
            return page.copy(items = videos)
        }
        next = page.nextCursor
        check(visited.add(next)) { "Boosty returned a repeated posts cursor" }
    }
}
