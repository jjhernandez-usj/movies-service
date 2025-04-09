package es.usj.androidapps.controllers

import es.usj.androidapps.model.dto.SingerDTO
import es.usj.androidapps.model.dto.CountDTO
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(value = "Singers", description = "Singers API", tags = ["Singers"])
@RequestMapping("singers")
interface SingerControllerApi {

    @ApiOperation(
        value = "Create a new singer.",
        nickname = "createSinger",
        notes = "Create a new singer.",
        response = SingerDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK.", response = SingerDTO::class),
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
    fun create(@RequestBody @Valid singerDTO: SingerDTO): ResponseEntity<SingerDTO>

    @ApiOperation(
        value = "Updates a singer.",
        nickname = "updateSinger",
        notes = "Updates a singer.",
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
    fun update(@RequestBody @Valid singerDTO: SingerDTO): ResponseEntity<CountDTO>

    @ApiOperation(
        value = "Deletes a singer.",
        nickname = "deleteSinger",
        notes = "Deletes a singer.",
        response = SingerDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK.", response = SingerDTO::class),
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
    fun delete(@PathVariable id: Long): ResponseEntity<SingerDTO>

    @ApiOperation(
        value = "Get singer by id.",
        nickname = "getSingerById",
        notes = "Get singer by id.",
        response = SingerDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK.", response = SingerDTO::class),
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
    fun getById(@PathVariable id: Long): ResponseEntity<SingerDTO>


    @ApiOperation(
        value = "Get singers.",
        nickname = "getSingers",
        notes = "Get all singers.",
        response = SingerDTO::class,
        responseContainer = "List"
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK.", response = SingerDTO::class, responseContainer = "List"),
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
        @RequestParam("limit", required = false) limit: Int? = 1000,
        @RequestParam("offset", required = false) offset: Long? = 0
    ): ResponseEntity<List<SingerDTO>>
}