package es.usj.androidapps.controllers

import es.usj.androidapps.model.dto.CountDTO
import es.usj.androidapps.model.dto.SongDTO
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(value = "Songs", description = "Songs API", tags = ["Songs"])
@RequestMapping("songs")
interface SongControllerApi {

    @ApiOperation(
        value = "Create a new song.",
        nickname = "createSong",
        notes = "Create a new song.",
        response = SongDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK.", response = SongDTO::class),
            ApiResponse(code = 400, message = "Invalid Credentials.", response = Error::class),
            ApiResponse(code = 401, message = "Unauthorized.", response = Error::class),
            ApiResponse(code = 403, message = "Forbidden.", response = Error::class),
            ApiResponse(code = 404, message = "Not found.", response = Error::class),
            ApiResponse(code = 500, message = "Server error.", response = Error::class)
        ]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(@RequestBody @Valid body: SongDTO): ResponseEntity<SongDTO>

    @ApiOperation(
        value = "Updates a song.",
        nickname = "updateSong",
        notes = "Updates a song.",
        response = CountDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK.", response = CountDTO::class),
            ApiResponse(code = 400, message = "Invalid Credentials.", response = Error::class),
            ApiResponse(code = 401, message = "Unauthorized.", response = Error::class),
            ApiResponse(code = 403, message = "Forbidden.", response = Error::class),
            ApiResponse(code = 404, message = "Not found.", response = Error::class),
            ApiResponse(code = 500, message = "Server error.", response = Error::class)
        ]
    )
    @RequestMapping(
        method = [RequestMethod.PUT],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun update(@RequestBody @Valid body: SongDTO): ResponseEntity<CountDTO>

    @ApiOperation(
        value = "Deletes a song.",
        nickname = "deleteSong",
        notes = "Deletes a song.",
        response = SongDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK.", response = SongDTO::class),
            ApiResponse(code = 400, message = "Invalid Credentials.", response = Error::class),
            ApiResponse(code = 401, message = "Unauthorized.", response = Error::class),
            ApiResponse(code = 403, message = "Forbidden.", response = Error::class),
            ApiResponse(code = 404, message = "Not found.", response = Error::class),
            ApiResponse(code = 500, message = "Server error.", response = Error::class)
        ]
    )
    @RequestMapping(
        value = ["/{id}"],
        method = [RequestMethod.DELETE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun delete(@PathVariable id: Long): ResponseEntity<SongDTO>

    @ApiOperation(
        value = "Get song by id.",
        nickname = "getSongById",
        notes = "Get song by id.",
        response = SongDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK.", response = SongDTO::class),
            ApiResponse(code = 400, message = "Invalid Credentials.", response = Error::class),
            ApiResponse(code = 401, message = "Unauthorized.", response = Error::class),
            ApiResponse(code = 403, message = "Forbidden.", response = Error::class),
            ApiResponse(code = 404, message = "Not found.", response = Error::class),
            ApiResponse(code = 500, message = "Server error.", response = Error::class)
        ]
    )
    @RequestMapping(
        path = ["/{id}"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getById(@PathVariable id: Long): ResponseEntity<SongDTO>


    @ApiOperation(
        value = "Get songs.",
        nickname = "getSongs",
        notes = "Get all songs",
        response = SongDTO::class,
        responseContainer = "List"
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK.", response = SongDTO::class, responseContainer = "List"),
            ApiResponse(code = 400, message = "Invalid Credentials.", response = Error::class),
            ApiResponse(code = 401, message = "Unauthorized.", response = Error::class),
            ApiResponse(code = 403, message = "Forbidden.", response = Error::class),
            ApiResponse(code = 404, message = "Not found.", response = Error::class),
            ApiResponse(code = 500, message = "Server error.", response = Error::class)
        ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAll(
        @RequestParam("limit", required = false) limit: Int? = 1500,
        @RequestParam("offset", required = false) offset: Long? = 0
    ): ResponseEntity<List<SongDTO>>
}