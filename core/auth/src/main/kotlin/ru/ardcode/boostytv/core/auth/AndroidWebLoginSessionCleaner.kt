package ru.ardcode.boostytv.core.auth

import android.webkit.CookieManager
import android.webkit.WebStorage
import ru.ardcode.boostytv.core.domain.auth.WebLoginSessionCleaner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume

/** Prevent the TV WebView from immediately signing back into the old account. */
class AndroidWebLoginSessionCleaner @Inject constructor() : WebLoginSessionCleaner {
    override suspend fun clear() = withContext(Dispatchers.Main) {
        WebStorage.getInstance().deleteAllData()
        val cookies = CookieManager.getInstance()
        suspendCancellableCoroutine { continuation ->
            cookies.removeAllCookies {
                if (continuation.isActive) continuation.resume(Unit)
            }
        }
        cookies.flush()
    }
}
