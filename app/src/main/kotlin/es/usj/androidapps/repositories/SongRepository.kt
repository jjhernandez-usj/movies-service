package es.usj.androidapps.repositories

import es.usj.androidapps.model.Song
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.stereotype.Repository

@Repository
interface SongRepository : JpaRepositoryImplementation<Song, Long> {
    fun findFirstByOrderByIdDesc() : Song
}