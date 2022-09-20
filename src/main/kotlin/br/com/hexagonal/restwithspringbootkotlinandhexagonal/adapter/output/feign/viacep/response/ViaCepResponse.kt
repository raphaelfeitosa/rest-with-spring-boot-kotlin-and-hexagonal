package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ViaCepResponse(
    @JsonProperty("logradouro")
    val street: String,
    @JsonProperty("bairro")
    val district: String,
    @JsonProperty("localidade")
    val city: String,
    @JsonProperty("uf")
    val state: String
)