package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.exceptions.annotations

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.ReportAsSingleViolation
import org.hibernate.validator.constraints.CompositionType
import org.hibernate.validator.constraints.ConstraintComposition
import org.hibernate.validator.constraints.br.CNPJ
import org.hibernate.validator.constraints.br.CPF
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [])
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
@CPF
@CNPJ
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class CPForCNPJ(
    val message: String = "This field must have a valid CPF or CNPJ format",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<Payload>> = []
)