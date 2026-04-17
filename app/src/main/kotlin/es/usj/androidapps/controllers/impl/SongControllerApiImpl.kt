package es.usj.androidapps.controllers.impl

import es.usj.androidapps.controllers.SongControllerApi
import es.usj.androidapps.model.dto.CountDTO
import es.usj.androidapps.model.dto.SongDTO
import es.usj.androidapps.services.SongServiceApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class SongControllerApiImpl : SongControllerApi {
    @Autowired
    lateinit var service: SongServiceApi

    override fun create(body: SongDTO): ResponseEntity<SongDTO> {
        return try {
            ResponseEntity.ok().body(service.save(body))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun update(body: SongDTO): ResponseEntity<CountDTO> {
        return try {
            val count = service.edit(body)
            ResponseEntity.ok().body(CountDTO(count))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun delete(id: Long): ResponseEntity<SongDTO> {
        return try {
            ResponseEntity.ok().body(service.delete(id))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun getById(id: Long): ResponseEntity<SongDTO> {
        return try {
            ResponseEntity.ok().body(service.find(id))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun getAll(limit: Int?, offset: Long?): ResponseEntity<List<SongDTO>> {
        return try {
            ResponseEntity.ok().body(service.list(limit, offset))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
}