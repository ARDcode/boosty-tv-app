package ru.ardcode.boostytv.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ardcode.boostytv.core.data.repository.DefaultBlogRepository
import ru.ardcode.boostytv.core.data.repository.DefaultFeedRepository
import ru.ardcode.boostytv.core.data.repository.SharedPreferencesPlaybackProgressRepository
import ru.ardcode.boostytv.core.domain.repository.BlogRepository
import ru.ardcode.boostytv.core.domain.repository.FeedRepository
import ru.ardcode.boostytv.core.domain.repository.PlaybackProgressRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindBlogRepository(impl: DefaultBlogRepository): BlogRepository

    @Binds
    abstract fun bindFeedRepository(impl: DefaultFeedRepository): FeedRepository

    @Binds
    abstract fun bindPlaybackProgress(impl: SharedPreferencesPlaybackProgressRepository): PlaybackProgressRepository
}
