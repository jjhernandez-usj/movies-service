package es.usj.androidapps.repositories

import es.usj.androidapps.model.Singer
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.stereotype.Repository

@Repository
interface SingerRepository : JpaRepositoryImplementation<Singer, Long> {
    fun findFirstByOrderByIdDesc() : Singer
}