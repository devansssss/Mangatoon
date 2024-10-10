package com.example.mangatoon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.mangatoon.room.model.Webtoon
import com.example.mangatoon.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {
    val favorites by viewModel.favoritesList.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Favorites",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (favorites.isEmpty()) {
            Text(
                text = "No favorite webtoons added yet.",
                style = MaterialTheme.typography.bodySmall,
            )
        } else {
            LazyColumn {
                items(favorites) { webtoon ->
                    WebtoonItem(webtoon) // Implement WebtoonItem to display webtoon details
                }
            }
        }
    }
}

@Composable
fun WebtoonItem(webtoon: Webtoon) {
    // Implement your Webtoon item UI similar to the HomeScreen
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = rememberImagePainter(webtoon.imageUrl),
            contentDescription = webtoon.title,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = webtoon.title, style = MaterialTheme.typography.bodyMedium)
            Text(text = webtoon.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}