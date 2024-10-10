package com.example.mangatoon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.mangatoon.viewmodel.DetailViewModel

@Composable
fun DetailScreen(webtoonTitle: String, viewModel: DetailViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = webtoonTitle) {
        viewModel.fetchWebtoonDetails(webtoonTitle)
    }

    val webtoonDetails by viewModel.webtoonDetails.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        webtoonDetails?.let { webtoon ->
            Image(
                painter = rememberImagePainter(webtoon.imageUrl),
                contentDescription = webtoon.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 0f,
                            endY = 1500f // Adjust as needed for the gradient
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                // Favorite Button
                var isFavorite by remember { mutableStateOf(false) } // State for favorite status

                IconButton(
                    onClick = {
                        isFavorite = !isFavorite
                        viewModel.toggleFavorite(webtoon)
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(8.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.8f))
                ) {
                    Icon(
                        imageVector = if (webtoon.isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = "Add to Favorites",
                        tint = if (webtoon.isFavorite) Color.Yellow else Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(250.dp)) // Spacer to push content below the image

                // Title
                Text(
                    text = webtoon.title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Description
                Text(
                    text = webtoon.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        } ?: run {
            // Handle loading or error state
            Text(text = "Loading...", modifier = Modifier.align(Alignment.Center))
        }
    }
}