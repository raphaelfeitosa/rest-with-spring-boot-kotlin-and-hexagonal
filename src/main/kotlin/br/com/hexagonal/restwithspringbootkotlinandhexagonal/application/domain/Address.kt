package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain

data class Address(
    var street: String? = null,
    var district: String? = null,
    var city: String? = null,
    var state: String? = null,
    val zipCode: String? = null,
    val number: String? = null
) {

    fun toClientAddress(client: Client): Address {
        client.address!!.street = this.street
        client.address.district = this.district
        client.address.state = this.state
        client.address.city = this.city
        return client.address
    }

}