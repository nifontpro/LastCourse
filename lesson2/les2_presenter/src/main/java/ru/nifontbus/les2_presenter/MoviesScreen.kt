package ru.nifontbus.les2_presenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import ru.nifontbus.les2_domain.model.Movie

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun MoviesScreen() {
    val viewModel: MoviesViewModel = hiltViewModel()
    val movies = viewModel.movies.collectAsLazyPagingItems()
    Scaffold(modifier = Modifier.fillMaxSize()) {
        ListContent(movies)
    }
}

@ExperimentalCoilApi
@Composable
private fun ListContent(movies: LazyPagingItems<Movie>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = movies,
           /* key = { movie ->
                movie.id
            }*/
        ) { movieItem ->
            movieItem?.let {
                MovieItem(it)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun MovieItem(movie: Movie) {
    val painter =
        rememberImagePainter(data = "https://image.tmdb.org/t/p/w500${movie.backdropPath}") {
            crossfade(durationMillis = 1000)
            error(R.drawable.ic_placeholder)
            placeholder(R.drawable.ic_placeholder)
        }
    Box(
        modifier = Modifier
            .clickable {
            }
            .height(300.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop
        )
    }
}

