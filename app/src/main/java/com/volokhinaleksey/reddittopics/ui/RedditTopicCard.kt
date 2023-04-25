package com.volokhinaleksey.reddittopics.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.volokhinaleksey.reddittopics.R
import com.volokhinaleksey.reddittopics.models.ui.PopularTopicUI

/**
 * A card for displaying information about a popular reddit topic.
 */

@Composable
fun RedditTopicCard(popularTopicUI: PopularTopicUI) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = popularTopicUI.title,
            modifier = Modifier.padding(bottom = 10.dp),
            color = Color.DarkGray
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "stars",
                    tint = Color(0xFFEECD30)
                )
                Text(
                    text = popularTopicUI.ups.toString(),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_comment_24),
                    contentDescription = "comments",
                    tint = Color(0xFF3E4FB7)
                )
                Text(
                    text = popularTopicUI.comments.toString(),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.height(1.dp)
        )
    }
}