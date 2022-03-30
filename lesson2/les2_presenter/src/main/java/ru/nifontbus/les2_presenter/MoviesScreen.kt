package ru.nifontbus.les2_presenter

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
    Log.e("my", "movie = $movie")
    val painter =
        rememberImagePainter(data = "https://image.tmdb.org/t/p/w500${movie.backdropPath}") {
            crossfade(durationMillis = 1000)
            error(R.drawable.ic_placeholder)
            placeholder(R.drawable.ic_placeholder)
        }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painter,
                contentDescription = "Movie Image",
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = "Зрительский рейтинг: ${movie.voteAverage}",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}

