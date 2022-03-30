package ru.nifontbus.les2_data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.nifontbus.les2_domain.model.Movie

@Serializable
@Entity
data class MoviesDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    val overview: String = "",
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("release_date")
    val releaseDate: String = "",
    val title: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
) {

    fun toMovie() = Movie(
        id = id,
        backdropPath = backdropPath,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage
    )
}
