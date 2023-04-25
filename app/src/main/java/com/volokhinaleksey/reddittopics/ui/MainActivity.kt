package com.volokhinaleksey.reddittopics.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.volokhinaleksey.reddittopics.network.NetworkStatus
import com.volokhinaleksey.reddittopics.ui.theme.RedditTopicsTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val networkStatus: NetworkStatus by inject()
    private val isNetworkAvailable = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = true) {
                networkStatus.networkObserve().collect {
                    isNetworkAvailable.value = it
                }
            }
            RedditTopicsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PopularTopicsScreen(networkAvailable = isNetworkAvailable.value)
                }
            }
        }
    }

}