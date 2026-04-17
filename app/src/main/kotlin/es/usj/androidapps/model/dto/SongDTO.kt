package es.usj.androidapps.model.dto

data class SongDTO(
    var id: Long = 0,
    var title: String = "",
    var album: String = "",
    var year: Int = 0,
    var runtime: Int = 0,
    var rating: Double = 0.0,
    var votes: Long = 0,
    var singers: List<Long> = emptyList(),
    var genres: List<Long> = emptyList()
) {
    init {
        title = title.trim()
    }

    constructor(
        id: Long,
        title: String,
        album: String,
        year: Int,
        runtime: Int,
        rating: Int,
        votes: Long,
        singers: List<Long> = emptyList(),
        genres: List<Long> = emptyList()
    ) : this(id, title, album, year, runtime, rating.toDouble(), votes, singers, genres)
}