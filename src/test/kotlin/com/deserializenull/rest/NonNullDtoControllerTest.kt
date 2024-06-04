package com.deserialize
import com.deserializenull.rest.dto.NonNullDto
import com.deserializenull.rest.dto.NullDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest
class NonNullDtoControllerTest(private val application: EmbeddedApplication<*>, @Client("/") httpClient: HttpClient): StringSpec({

    "test the server is running" {
        assert(application.isRunning)
    }

    "test the deserialization of missing fields" {
        // when
        val e = shouldThrow<HttpClientResponseException> {
            val exchange = httpClient.toBlocking().exchange(HttpRequest.POST("nonnulldto", NullDto()), NullDto::class.java)
            exchange.body.get().longField shouldNotBe 0L
        }

        // then
        e.message shouldContain "longField"
    }
})
