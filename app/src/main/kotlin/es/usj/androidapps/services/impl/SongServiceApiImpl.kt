package es.usj.androidapps.services.impl

import es.usj.androidapps.model.dto.SongDTO
import es.usj.androidapps.repositories.SingerRepository
import es.usj.androidapps.repositories.GenreRepository
import es.usj.androidapps.repositories.SongRepository
import es.usj.androidapps.services.SongServiceApi
import es.usj.androidapps.utils.DataConverter
import es.usj.androidapps.utils.OffsetBasedPageRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SongServiceApiImpl : SongServiceApi {

    @Autowired
    lateinit var songRepository: SongRepository

    @Autowired
    lateinit var genreRepository: GenreRepository

    @Autowired
    lateinit var singerRepository: SingerRepository

    override fun list(limit: Int?, offset: Long?): List<SongDTO> {
        if (limit != null && offset != null) {
            val pageable = OffsetBasedPageRequest(
                offset,
                limit
            )
            return songRepository.findAll(pageable).toList().map { DataConverter.songToDTO(it) }
        }
        return songRepository.findAll().map { DataConverter.songToDTO(it) }
    }

    override fun find(id: Long): SongDTO? {
        return DataConverter.songToDTO(songRepository.findById(id).get())
    }

    override fun delete(id: Long): SongDTO {
        val song = find(id)
        if (song != null) {
            songRepository.deleteById(id)
            return song
        } else {
            throw Exception("Song not found")
        }
    }

    override fun save(element: SongDTO): SongDTO {
        val item = DataConverter.songFromDTO(element)
        val singers = element.singers.map { singerRepository.findById(it).get() }
        val genres = element.genres.map { genreRepository.findById(it).get() }
        item.addAll(singers.toMutableList())
        item.addAllGenres(genres.toMutableList())
        item.id = songRepository.findFirstByOrderByIdDesc().id + 1
        return DataConverter.songToDTO(songRepository.save(item))
    }

    override fun edit(element: SongDTO): Int {
        val item = DataConverter.songFromDTO(element)
        val genres = element.genres.map { genreRepository.findById(it).get() }
        val singers = element.singers.map { singerRepository.findById(it).get() }
        item.addAllGenres(genres)
        item.addAll(singers)
        songRepository.save(item)
        return 1
    }
}