package es.usj.androidapps

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import es.usj.androidapps.model.Singer
import es.usj.androidapps.model.Genre
import es.usj.androidapps.model.Song
import es.usj.androidapps.repositories.SingerRepository
import es.usj.androidapps.repositories.GenreRepository
import es.usj.androidapps.repositories.SongRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.core.io.ResourceLoader
import java.text.SimpleDateFormat
import javax.annotation.PostConstruct

@EntityScan(basePackages = ["es.usj.androidapps"])
@SpringBootApplication
class SongsServiceMsApplication {

    @Autowired
    lateinit var resourceLoader: ResourceLoader
    @Autowired
    lateinit var sservice: SongRepository
    @Autowired
    lateinit var gservice: GenreRepository
    @Autowired
    lateinit var aservice: SingerRepository
    @PostConstruct
    fun init() {
        val reader = csvReader {
            delimiter = '|'
        }

        val genres = mutableMapOf<String, Genre>()
        val singers = mutableMapOf<String, Singer>()
        val songs = mutableMapOf<Long, Song>()
        val resource = resourceLoader.getResource("classpath:/data.csv")

        val inputStream = resource.inputStream
        var count = 0
        reader.open(inputStream) {
            readAllAsSequence(10).forEach { row ->
                try {
                    if (count != 0) {
                        val genresInSong = cleanRow(row[2], "").split(',')
                        val singersInSong = cleanRow(row[4], "").split(',')
                        val gim = mutableListOf<Genre>()
                        val sim = mutableListOf<Singer>()
                        genresInSong.forEach {
                            if (genres[it] == null) {
                                genres[it] = Genre(genres.size.toLong() + 1, it)
                                genres[it]?.let { it1 -> gservice.save(it1) }
                            }
                            gim.add(genres[it]!!)
                        }
                        singersInSong.forEach {
                            if (singers[it] == null) {
                                singers[it] = Singer(singers.size.toLong() + 1, it)
                                singers[it]?.let { it1 -> aservice.save(it1) }
                            }
                            sim.add(singers[it]!!)
                        }
                        val song = Song()
                        song.id = cleanRow(row[0], "0").toLong()
                        song.title = cleanRow(row[1], "No title")
                        song.album = cleanRow(row[3], "No album")
                        song.year = cleanRow(row[5], "0").toInt()
                        val timeParts = cleanRow(row[6], "0").split(":")
                        val minutes = timeParts[0].toInt()
                        val seconds = timeParts[1].toInt()
                        val totalSeconds = minutes * 60 + seconds
                        song.runtime = totalSeconds
                        song.rating = cleanRow(row[7], "0.0").toDouble()
                        song.votes = cleanRow(row[8], "0").toLong()
                        song.addAllGenres(gim)
                        song.addAll(sim)
                        songs[song.id] = song
                        sservice.save(song)
                    }
                    count++
                } catch (e: Exception) {
                    println("Error in row $count")
                }
            }
        }
    }
}

fun main(args: Array<String>) {

    runApplication<SongsServiceMsApplication>(*args)
}

fun cleanRow(row: String, default: String) : String {
    var content = row.replace(";,","")
    content = content.replace(";","")
    return content.ifEmpty { default }
}