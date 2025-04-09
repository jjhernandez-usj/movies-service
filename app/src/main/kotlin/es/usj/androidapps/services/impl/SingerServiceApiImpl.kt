package es.usj.androidapps.services.impl

import es.usj.androidapps.model.dto.SingerDTO
import es.usj.androidapps.repositories.SingerRepository
import es.usj.androidapps.services.SingerServiceApi
import es.usj.androidapps.utils.DataConverter
import es.usj.androidapps.utils.OffsetBasedPageRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SingerServiceApiImpl : SingerServiceApi {

    @Autowired
    lateinit var singerRepository: SingerRepository

    override fun list(limit: Int?, offset: Long?): List<SingerDTO> {
        if (limit != null && offset != null) {
            val pageable = OffsetBasedPageRequest(
                offset,
                limit
            )
            return singerRepository.findAll(pageable).toList().map { DataConverter.singerToDTO(it) }
        }
        return singerRepository.findAll().map { DataConverter.singerToDTO(it) }
    }

    override fun find(id: Long): SingerDTO? {
        return DataConverter.singerToDTO(singerRepository.findById(id).get())
    }

    override fun delete(id: Long): SingerDTO {
        val singer = find(id)
        if (singer != null) {
            singerRepository.deleteById(id)
            return singer
        } else {
            throw Exception("Singer not found")
        }
    }

    override fun save(element: SingerDTO): SingerDTO {
        val item = DataConverter.singerFromDTO(element)
        item.id = singerRepository.findFirstByOrderByIdDesc().id + 1
        return DataConverter.singerToDTO(singerRepository.save(item))
    }

    override fun edit(element: SingerDTO): Int {
        val item = DataConverter.singerFromDTO(element)
        val singer = singerRepository.findById(item.id).get()
        singer.name = item.name
        singerRepository.save(singer)
        return 1
    }
}