package es.usj.androidapps

import java.io.File

fun main() {
    val inputFile = "app/src/main/resources/original.csv"
    val outputFile = "app/src/main/resources/data.csv"

    val outputLines = mutableListOf("Rank;|;Title;|;Genre;|;Album;|;Singers;|;Year;|;Runtime (Minutes);|;Rating;|;Votes;|;Metascore")

    File(inputFile).useLines { lines ->
        lines.drop(1).forEach { line -> // Ignorar la cabecera
            val regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)".toRegex()
            val values = line.split(regex).map { it.trim().removeSurrounding("\"") }

            val rank = values[0]
            val title = values[1]
            val singers = values[2].ifEmpty { "Unknown" }
            val genre = values[5].ifEmpty { "Unknown" }
            val album = values[7].ifEmpty { "Unknown Album" }
            val year = values[8].take(4)
            val runtime = values[9]
            val rating = values[4]
            val votes = (Math.random() * 10000000).toInt()
            val metascore = values[3]

            val formattedLine = "$rank;|;$title;|;$genre;|;$album;|;$singers;|;$year;|;$runtime;|;$rating;|;$votes;|;$metascore"
            outputLines.add(formattedLine)
        }
    }

    File(outputFile).writeText(outputLines.joinToString("\n"))
    println("Archivo generado: $outputFile")
}