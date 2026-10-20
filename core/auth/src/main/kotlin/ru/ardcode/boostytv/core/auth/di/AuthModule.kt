package ru.ardcode.boostytv.core.auth.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.ardcode.boostytv.core.auth.EncryptedTokenStore
import ru.ardcode.boostytv.core.auth.AndroidWebLoginSessionCleaner
import ru.ardcode.boostytv.core.auth.SessionParser
import ru.ardcode.boostytv.core.auth.BuildConfig
import ru.ardcode.boostytv.core.auth.pairing.LanAddress
import ru.ardcode.boostytv.core.auth.pairing.PairingServer
import ru.ardcode.boostytv.core.domain.auth.pairing.PairingService
import ru.ardcode.boostytv.core.domain.auth.SessionDecoder
import ru.ardcode.boostytv.core.domain.auth.WebLoginSessionCleaner
import ru.ardcode.boostytv.core.domain.auth.SessionManager
import ru.ardcode.boostytv.core.domain.auth.TokenRefresher
import ru.ardcode.boostytv.core.domain.auth.TokenStore
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun providePairingFactory(@ApplicationContext context: Context, json: Json): PairingService.Factory =
        PairingService.Factory { scope ->
            PairingServer(
                json = json,
                scope = scope,
                lanAddress = LanAddress(context),
                advertisedHost = BuildConfig.PAIRING_HOST.takeIf { it.isNotBlank() },
                listenPort = BuildConfig.PAIRING_PORT,
            )
        }

    @Provides
    fun provideWebLoginSessionCleaner(impl: AndroidWebLoginSessionCleaner): WebLoginSessionCleaner = impl

    @Provides
    fun provideSessionDecoder(json: Json): SessionDecoder =
        SessionDecoder { rawAuth, clientId -> SessionParser.parse(json, rawAuth, clientId) }

    @Provides
    @Singleton
    fun provideTokenStore(
        @ApplicationContext context: Context,
    ): TokenStore = EncryptedTokenStore(context, Dispatchers.IO)

    /**
     * [TokenRefresher] is bound in :core:network -- see NetworkModule. Hilt
     * resolves it across module boundaries because there is a single component
     * graph.
     */
    @Provides
    @Singleton
    fun provideSessionManager(
        store: TokenStore,
        refresher: TokenRefresher,
    ): SessionManager = SessionManager(store, refresher)
}
