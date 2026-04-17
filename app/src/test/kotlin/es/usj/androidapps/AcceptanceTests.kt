package es.usj.androidapps

import com.fasterxml.jackson.module.kotlin.jsonMapper
import es.usj.androidapps.infrastructure.BaseTest
import es.usj.androidapps.infrastructure.TestProperties
import es.usj.androidapps.model.dto.SingerDTO
import es.usj.androidapps.model.dto.CountDTO
import es.usj.androidapps.model.dto.GenreDTO
import es.usj.androidapps.model.dto.SongDTO
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

const val SINGERS_PATH = "/singers"
const val GENRE_PATH = "/genres"
const val SONGS_PATH = "/songs"

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AcceptanceTests : BaseTest(TestProperties.local()) {

    @Test
    fun `add singer without id returns singer with maximum id`() {
        val items = SINGERS_PATH.GET<SingerDTO>(jsonMapper())
        val size = items.count()
        val item = SingerDTO(0, "Juanjo")
        val returned = SINGERS_PATH.POST<SingerDTO>(item)
        assert(returned.id > size)
        assert(SINGERS_PATH.GET<SingerDTO>(jsonMapper()).count() > size)
    }

    @Test
    fun `edit singer returns singer properly`() {
        val item = SingerDTO(0, "Juanjo${Math.random()}")
        val created = SINGERS_PATH.POST<SingerDTO>(item)
        created.name = "Juanjo${Math.random()}"
        val size = SINGERS_PATH.PUT<CountDTO>(created)
        assert(size.count == 1)
        val edited = "$SINGERS_PATH/${created.id}".GET<SingerDTO>()
        assert(edited.id == created.id)
        assert(edited.name == created.name)
    }

    @Test
    fun `delete an singer by id works`() {
        val item = SingerDTO(0, "Juanjo${Math.random()}")
        val created = SINGERS_PATH.POST<SingerDTO>(item)
        val found = "$SINGERS_PATH/${created.id}".GET<SingerDTO>()
        "$SINGERS_PATH/${found.id}".DELETE<SingerDTO>()
        assertThrows<Exception> { "$SINGERS_PATH/${created.id}".GET<SingerDTO>() }
    }

    @Test
    fun `list singers works`() {
        val found = SINGERS_PATH.GET<SingerDTO>(jsonMapper())
        assert(found.isNotEmpty())
    }

    @Test
    fun `add genre without id returns genre with maximum id`() {
        val items = GENRE_PATH.GET<GenreDTO>(jsonMapper())
        val size = items.count()
        val item =  GenreDTO(0, "Action${Math.random()}")
        val returned = GENRE_PATH.POST<GenreDTO>(item)
        assert(returned.id > size)
        assert(GENRE_PATH.GET<GenreDTO>(jsonMapper()).count() > size)
    }

    @Test
    fun `edit genre returns genre properly`() {
        val item = GenreDTO(0, "Action${Math.random()}")
        val created = GENRE_PATH.POST<GenreDTO>(item)
        created.name = "Juanjo${Math.random()}"
        val size = GENRE_PATH.PUT<CountDTO>(created)
        assert(size.count == 1)
        val edited = "$GENRE_PATH/${created.id}".GET<GenreDTO>()
        assert(edited.id == created.id)
        assert(edited.name == created.name)
    }

    @Test
    fun `delete a genre by id works`() {
        val item = GenreDTO(0, "Action${Math.random()}")
        val created = GENRE_PATH.POST<GenreDTO>(item)
        val found = "$GENRE_PATH/${created.id}".GET<GenreDTO>()
        "$GENRE_PATH/${found.id}".DELETE<GenreDTO>()
        assertThrows<Exception> { "$GENRE_PATH/${created.id}".GET<GenreDTO>() }
    }

    @Test
    fun `list genre works`() {
        val found = GENRE_PATH.GET<GenreDTO>(jsonMapper())
        assert(found.isNotEmpty())
    }

    fun createSongDTO() : SongDTO {
        return SongDTO(0L,
            "Title${Math.random()}",
            "Album${Math.random()}",
            (Math.random()*2022).toInt(),
            (Math.random()*180).toInt(),
            Math.random(),
            (Math.random() * 10000000).toLong(),
            listOf(1,2,3),
            listOf(4,2)
        )
    }

    @Test
    fun `add song without id returns song with maximum id`() {
        val items = SONGS_PATH.GET<SongDTO>(jsonMapper())
        val size = items.count()
        val item = createSongDTO()
        val returned = SONGS_PATH.POST<SongDTO>(item)
        assert(returned.id > size)
        assert(returned.singers.size == item.singers.size)
        assert(returned.singers.sorted().toString() == item.singers.sorted().toString())
        assert(returned.genres.size == item.genres.size)
        assert(returned.genres.sorted().toString() == item.genres.sorted().toString())
        assert(SONGS_PATH.GET<SongDTO>(jsonMapper()).count() > size)
    }

    @Test
    fun `add song id returns song with maximum id`() {
        val items = SONGS_PATH.GET<SongDTO>(jsonMapper())
        val size = items.count()
        val item = createSongDTO()
        val returned = SONGS_PATH.POST<SongDTO>(item)
        assert(returned.id > size)
        assert(returned.singers.size == item.singers.size)
        assert(returned.singers.sorted().toString() == item.singers.sorted().toString())
        assert(returned.genres.size == item.genres.size)
        assert(returned.genres.sorted().toString() == item.genres.sorted().toString())
        assert(SONGS_PATH.GET<SongDTO>(jsonMapper()).count() > size)
    }

    @Test
    fun `edit song returns song properly`() {
        val item = createSongDTO()
        val created = SONGS_PATH.POST<SongDTO>(item)
        created.title = "Matrix${Math.random()}"
        val size = SONGS_PATH.PUT<CountDTO>(created)
        assert(size.count == 1)
        val edited = "$SONGS_PATH/${created.id}".GET<SongDTO>()
        assert(edited.id == created.id)
        assert(edited.title == created.title)
        assert(edited.singers.size == item.singers.size)
        assert(edited.singers.sorted().toString() == item.singers.sorted().toString())
        assert(edited.genres.size == item.genres.size)
        assert(edited.genres.sorted().toString() == item.genres.sorted().toString())
    }

    @Test
    fun `edit song title returns song properly`() {
        val edited = "$SONGS_PATH/1".GET<SongDTO>()
        edited.title = "New guardians"
        val size = SONGS_PATH.PUT<CountDTO>(edited)
        assert(size.count == 1)
        val created = "$SONGS_PATH/1".GET<SongDTO>()
        assert(edited.id == created.id)
        assert(edited.title == created.title)
    }


    @Test
    fun `delete a song by id works`() {
        val item = createSongDTO()
        val created = SONGS_PATH.POST<SongDTO>(item)
        val found = "$SONGS_PATH/${created.id}".GET<SongDTO>()
        "$SONGS_PATH/${found.id}".DELETE<SongDTO>()
        assertThrows<Exception> { "$SONGS_PATH/${created.id}".GET<SongDTO>() }
    }

    @Test
    fun `list song works`() {
        val found = SONGS_PATH.GET<SongDTO>(jsonMapper())
        assert(found.isNotEmpty())
    }
}