package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.validation.CPForCNPJ
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.validation.ExistsDocumentNumber
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.Valid
import jakarta.validation.constraints.*
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateClientRequest(
    @field:[NotNull NotBlank] val name: String?,
    @field:[NotNull NotBlank] val gender: String?,
    @field:[NotNull NotBlank Email] val email: String?,
    @field:[NotNull NotBlank CPForCNPJ ExistsDocumentNumber] val documentNumber: String?,
    @field:[NotNull Pattern(regexp = "CPF")] val documentType: String?,
    @field:[NotNull
    DecimalMin(value = "0.01", inclusive = true)
    Digits(integer = 15, fraction = 2)] val salary: BigDecimal?,
    @field:[NotNull Valid] val address: Address?,
    val additionalInformation: Map<String, String>? = mapOf()
) {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Address(
        @field:[NotNull Pattern(regexp = "^[0-9]{8}\$")] val zipCode: String?,
        @field:[NotNull NotBlank] val number: String?
    )

}