package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.exceptions

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class ExceptionHandler(
    private val messageSource: MessageSource,
) {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException,
        request: WebRequest,
    ): ErrorMessageResponse {
        val errorMessage = hashMapOf<String, String?>()
        exception.bindingResult.fieldErrors.forEach {
            errorMessage[it.field] = messageSource.getMessage(it, LocaleContextHolder.getLocale())
        }
        return ErrorMessageResponse(
            code = HttpStatus.BAD_REQUEST.name,
            message = "Method argument not valid",
            path = request.getDescription(false),
            errors = errorMessage
        )
    }

}