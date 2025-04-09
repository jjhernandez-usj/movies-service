package es.usj.androidapps.utils

import es.usj.androidapps.model.Singer
import es.usj.androidapps.model.Genre
import es.usj.androidapps.model.Song
import es.usj.androidapps.model.dto.SingerDTO
import es.usj.androidapps.model.dto.GenreDTO
import es.usj.androidapps.model.dto.SongDTO

object DataConverter {

    fun singerToDTO(singer: Singer): SingerDTO {
        return SingerDTO(
            singer.id,
            singer.name
        )
    }

    fun singerFromDTO(singerDTO: SingerDTO): Singer {
        return Singer(
            singerDTO.id,
            singerDTO.name,
            mutableListOf()
        )
    }

    fun genreToDTO(genre: Genre): GenreDTO {
        return GenreDTO(
            genre.id,
            genre.name
        )
    }

    fun genreFromDTO(genreDTO: GenreDTO): Genre {
        return Genre(
            genreDTO.id,
            genreDTO.name,
            mutableListOf()
        )
    }

    fun songToDTO(song: Song): SongDTO {
        return SongDTO(
            song.id,
            song.title,
            song.album,
            song.year,
            song.runtime,
            song.rating,
            song.votes,
            song.singers.map { it.id },
            song.genres.map { it.id }
        )
    }

    fun songFromDTO(songDTO: SongDTO): Song {
        return Song(
            songDTO.id,
            songDTO.title,
            songDTO.album,
            songDTO.year,
            songDTO.runtime,
            songDTO.rating,
            songDTO.votes,
            mutableListOf(),
            mutableListOf()
        )
    }
}