package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.config

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.exceptions.BusinessException
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.exceptions.ErrorMessageResponse
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.exceptions.NotFoundException
import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.Util
import feign.codec.ErrorDecoder
import org.slf4j.LoggerFactory
import java.nio.charset.Charset

class FeignDefaultErrorDecoder : ErrorDecoder {

    private val logger = LoggerFactory.getLogger(FeignDefaultErrorDecoder::class.java.name)

    override fun decode(key: String, response: Response): Exception {
        val streamResponse = Util.toString(response.body().asReader(Charset.defaultCharset()))

        val errorResponse = try {
            streamResponse.jsonToObject()
        } catch (e: Exception) {
            ErrorMessageResponse(
                message = streamResponse,
                path = response.request().url(),
                code = response.status().toString()
            )
        }

        when (response.status()) {
            404 -> {
                logger.error("Error feign integration with code: [{}], methodKey: [{}]", response.status(), key)
                throw NotFoundException("Not found ${feignErrorResponse(errorResponse.code, errorResponse.message)}")
            }
            400, 422 -> {
                logger.error("Error feign integration with code: [{}], methodKey: [{}]", response.status(), key)
                throw BusinessException("Business ${feignErrorResponse(errorResponse.code, errorResponse.message)}")
            }
            else -> {
                logger.error("Integration error with code: [{}], methodKey: [{}]", response.status(), key)
                throw RuntimeException("Integration error: URL [${response.request().url()}] and response [$streamResponse]")
            }
        }
    }

    private  fun feignErrorResponse(code: String?, message: String?): String =
        "Integration error with code: [${code ?: "no code body response"}] and message: [${message ?: "no message body response"}]"

    private fun String.jsonToObject() = ObjectMapper().readValue(this, ErrorMessageResponse::class.java)
}