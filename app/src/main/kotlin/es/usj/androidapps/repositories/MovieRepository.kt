package es.usj.androidapps.repositories

import es.usj.androidapps.model.Movie
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import java.util.UUID

interface MovieRepository : JpaRepositoryImplementation<Movie, UUID> {
}