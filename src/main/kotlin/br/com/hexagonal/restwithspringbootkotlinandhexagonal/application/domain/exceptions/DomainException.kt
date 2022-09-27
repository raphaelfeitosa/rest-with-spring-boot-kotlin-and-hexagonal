package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.exceptions

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.exceptions.BusinessException

class ViaCepPortException(override val message: String?) : BusinessException(message)