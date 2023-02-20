package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.validation

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.errors.Errors.DOCUMENT_NUMBER_ALREADY_EXISTS
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.exceptions.BusinessException
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.repository.ClientRepository
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [ExistsDocumentNumberValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
annotation class ExistsDocumentNumber(
    val message: String = "",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
)

class ExistsDocumentNumberValidator(
    private val clientRepository: ClientRepository,
) : ConstraintValidator<ExistsDocumentNumber, String> {

    override fun isValid(documentNumber: String?, context: ConstraintValidatorContext?): Boolean {
        when {
            documentNumber != null ->
                if (clientRepository.findByDocumentNumberAndActiveTrue(documentNumber).isPresent)
                    throw BusinessException(DOCUMENT_NUMBER_ALREADY_EXISTS)
        }
        return true
    }

}