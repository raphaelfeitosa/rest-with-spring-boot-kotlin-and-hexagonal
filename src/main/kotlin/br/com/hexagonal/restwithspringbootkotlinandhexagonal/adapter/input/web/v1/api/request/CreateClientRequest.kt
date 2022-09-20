package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.exceptions.annotations.CPForCNPJ
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateClientRequest(
    @field:[NotBlank] val name: String?,
    @field:[NotBlank] val gender: String?,
    @field:[NotBlank Email] val email: String?,
    @field:[NotNull NotBlank CPForCNPJ] val documentNumber: String?,
    @field:[NotNull Pattern(regexp = "CPF")] val documentType: String?,
    @field:[NotNull Positive] val salary: BigDecimal?,
    @field:[NotNull Valid] val address: Address?
) {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Address(
        @field:[NotNull NotBlank Pattern(regexp = "[0-9]{8}")] val zipCode: String?,
        @field:[NotNull NotBlank] val number: String?
    )

}