package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain


data class Address(
    var street: String,
    var district: String,
    var city: String,
    var state: String
)