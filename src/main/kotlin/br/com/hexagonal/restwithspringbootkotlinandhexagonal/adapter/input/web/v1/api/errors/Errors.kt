package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.errors

object Errors {
    const val METHOD_ARGUMENT_NOT_VALID = "Method argument not valid"
    const val RESOURCE_NOT_FOUND = "Resource not found"
    const val DOCUMENT_NUMBER_ALREADY_EXISTS = "Document number already exists"
    private const val INVALID_CEP_INTEGRATION = "invalid or does not exist"

    fun invalidCepIntegration(zipCode: String) = "Zipcode: $zipCode $INVALID_CEP_INTEGRATION"
}