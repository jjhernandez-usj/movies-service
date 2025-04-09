package es.usj.androidapps.controllers.impl

import es.usj.androidapps.controllers.SingerControllerApi
import es.usj.androidapps.model.dto.SingerDTO
import es.usj.androidapps.model.dto.CountDTO
import es.usj.androidapps.services.SingerServiceApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class SingerControllerApiImpl : SingerControllerApi {

    @Autowired
    lateinit var service: SingerServiceApi

    override fun create(singerDTO: SingerDTO): ResponseEntity<SingerDTO> {
        return try {
            ResponseEntity.ok().body(service.save(singerDTO))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun update(singerDTO: SingerDTO): ResponseEntity<CountDTO> {
        return try {
            val count = CountDTO(service.edit(singerDTO))
            ResponseEntity.ok().body(count)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun delete(id: Long): ResponseEntity<SingerDTO> {
        return try {
            ResponseEntity.ok().body(service.delete(id))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun getById(id: Long): ResponseEntity<SingerDTO> {
        return try {
            ResponseEntity.ok().body(service.find(id))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    override fun getAll(limit: Int?, offset: Long?): ResponseEntity<List<SingerDTO>> {
        return try {
            ResponseEntity.ok().body(service.list(limit, offset))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
}