package com.volokhinaleksey.reddittopics.app

import android.app.Application
import com.volokhinaleksey.reddittopics.di.databaseModule
import com.volokhinaleksey.reddittopics.di.networkModule
import com.volokhinaleksey.reddittopics.di.popularTopicScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RedditTopicsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(databaseModule, networkModule, popularTopicScreen))
        }
    }

}