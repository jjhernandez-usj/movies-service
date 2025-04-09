package es.usj.androidapps.model

import javax.persistence.*

@Entity
@Table(name = "genres")
class Genre(
    @Id
    var id: Long,
    @Column(unique = true)
    var name: String,
    @ManyToMany(mappedBy = "genres")
    val songs: MutableList<Song> = mutableListOf()
) {
    constructor() : this(0, "")
}