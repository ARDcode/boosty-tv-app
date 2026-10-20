package ru.ardcode.boostytv.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ardcode.boostytv.core.domain.repository.BlogRepository
import ru.ardcode.boostytv.core.domain.repository.FeedRepository
import ru.ardcode.boostytv.core.domain.usecase.GetHomeFeed

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideHomeFeed(feeds: FeedRepository, blogs: BlogRepository): GetHomeFeed = GetHomeFeed(feeds, blogs)
}
