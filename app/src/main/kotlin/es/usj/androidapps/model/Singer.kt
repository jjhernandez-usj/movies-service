package es.usj.androidapps.model

import javax.persistence.*

@Entity
@Table(name = "singers")
class Singer(
    @Id
    var id: Long,
    @Column(unique = true)
    var name: String,
    @ManyToMany(mappedBy = "singers")
    val songs: MutableList<Song> = mutableListOf()
) {
    constructor() : this(0, "")
}