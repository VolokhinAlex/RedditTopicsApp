package com.volokhinaleksey.reddittopics.di

import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.volokhinaleksey.reddittopics.datasource.popular.LocalPopularTopicsDataSource
import com.volokhinaleksey.reddittopics.datasource.popular.LocalPopularTopicsDataSourceImpl
import com.volokhinaleksey.reddittopics.datasource.popular.PopularTopicsDataSource
import com.volokhinaleksey.reddittopics.datasource.popular.RemotePopularTopicsDataSource
import com.volokhinaleksey.reddittopics.interactor.MainInteractor
import com.volokhinaleksey.reddittopics.interactor.MainInteractorImpl
import com.volokhinaleksey.reddittopics.models.remote.PopularTopicDTO
import com.volokhinaleksey.reddittopics.network.AndroidNetworkStatus
import com.volokhinaleksey.reddittopics.network.ApiHolder
import com.volokhinaleksey.reddittopics.network.NetworkStatus
import com.volokhinaleksey.reddittopics.network.RedditApiHolder
import com.volokhinaleksey.reddittopics.network.RedditApiService
import com.volokhinaleksey.reddittopics.repository.PopularTopicsRepository
import com.volokhinaleksey.reddittopics.repository.PopularTopicsRepositoryImpl
import com.volokhinaleksey.reddittopics.room.RedditDatabase
import com.volokhinaleksey.reddittopics.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val DB_NAME = "reddit_topics.db"

val databaseModule = module {

    /**
     * Providing a dependency for room database
     */

    single {
        Room
            .databaseBuilder(get(), RedditDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {

    single<NetworkStatus> {
        AndroidNetworkStatus(get())
    }

    /**
     * Providing a dependency for ApiHolder
     */

    single<ApiHolder> { RedditApiHolder(get()) }

    /**
     * Providing a dependency for the gson converter
     */

    single<Gson> {
        GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    /**
     * Providing a dependency for OkHttpClient with HttpLoggingInterceptor
     */

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    /**
     * Providing a dependency to work with retrofit
     */

    single {
        Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
            .create(RedditApiService::class.java)
    }
}

val popularTopicScreen = module {

    /**
     * Providing a dependency for remote datasource
     */

    single<PopularTopicsDataSource<PopularTopicDTO>> {
        RemotePopularTopicsDataSource(get())
    }

    /**
     * Providing a dependency for local datasource
     */

    single<LocalPopularTopicsDataSource> {
        LocalPopularTopicsDataSourceImpl(get())
    }

    /**
     * Providing a dependency for Popular Topics Repository
     */

    factory<PopularTopicsRepository> {
        PopularTopicsRepositoryImpl(
            get(),
            get()
        )
    }

    /**
     * Providing a dependency for Main Interactor
     */

    factory<MainInteractor> { MainInteractorImpl(get()) }

    /**
     * Providing a dependency for MainViewModel
     */

    viewModel { MainViewModel(get()) }
}