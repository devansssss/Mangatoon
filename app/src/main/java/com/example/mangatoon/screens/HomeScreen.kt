package com.example.mangatoon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.mangatoon.room.model.Webtoon
import com.example.mangatoon.viewmodel.WebtoonViewModel

@Composable
fun HomeScreen(viewModel: WebtoonViewModel = hiltViewModel(), onItemClick: (Webtoon) -> Unit, navigateToFavorites: () -> Unit) {
    val webtoons by viewModel.webtoonList.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(6.dp)) {
        Text(
            text = "Webtoon List",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { navigateToFavorites() },
            modifier = Modifier
                .align(Alignment.End) // Align button to the end
                .padding(16.dp)
        ) {
            Text(text = "View Favorites")
        }

        if (webtoons.isEmpty()) {
            Text(
                text = "No Webtoons available",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn {
                items(webtoons) { webtoon ->
                    // Pass the onItemClick function to WebtoonItem
                    WebtoonItem(webtoon = webtoon, onItemClick = {
                        onItemClick(webtoon)
                    })
                }
            }
        }
    }
}

@Composable
fun WebtoonItem(webtoon: Webtoon, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onItemClick), // Make the card clickable
        shape = MaterialTheme.shapes.medium
    ) {
        Box {
            Image(
                painter = rememberImagePainter(webtoon.imageUrl),
                contentDescription = webtoon.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )
            // Gradient overlay
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 10f,
                            endY = 400f // Adjusted to bring the gradient lower
                        )
                    )
            )
            Text(
                text = webtoon.title,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, color = Color.White),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}