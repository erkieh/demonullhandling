package com.deserializenull.rest

import com.deserializenull.rest.dto.NonNullDto
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.validation.Valid

@Validated
@Controller("/nonnulldto")
class NonNullDtoController(
) {

    @Post
    fun post(@Valid @Body request: NonNullDto): NonNullDto {
        return request
    }

}