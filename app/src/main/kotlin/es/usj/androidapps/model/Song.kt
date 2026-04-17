package es.usj.androidapps.model

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import javax.persistence.*

@Entity
@Table(name = "songs")
class Song(
    @Id
    var id: Long,
    @Column(columnDefinition = "TEXT")
    var title: String,
    @Column
    var album: String,
    @Column(name = "release_year")
    var year: Int,
    @Column
    var runtime: Int,
    @Column
    var rating: Double,
    @Column
    var votes: Long,
    @ManyToMany(cascade = [CascadeType.DETACH])
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
        name = "singers_to_songs",
        joinColumns = [JoinColumn(name = "id_singer")],
        inverseJoinColumns = [JoinColumn(name = "id_song")]
    )
    var singers: MutableList<Singer> = mutableListOf(),
    @ManyToMany(cascade = [CascadeType.DETACH])
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
        name = "genres_to_songs",
        joinColumns = [JoinColumn(name = "id_genre")],
        inverseJoinColumns = [JoinColumn(name = "id_song")]
    )
    var genres: MutableList<Genre> = mutableListOf()
) {
    constructor() : this(0, "", "", 0, 0, 0.0, 0)

    fun addAllGenres(genres: List<Genre>) {
        genres.forEach { addGenre(it) }
    }

    fun addGenre(genre: Genre) {
        genre.songs.add(this)
        this.genres.add(genre)
    }

    fun addAll(singers: List<Singer>) {
        singers.forEach { add(it) }
    }

    fun add(singer: Singer) {
        singer.songs.add(this)
        this.singers.add(singer)
    }
}